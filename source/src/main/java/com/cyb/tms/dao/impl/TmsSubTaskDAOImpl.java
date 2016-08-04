package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSubTaskDAOImpl implements TmsSubTaskDAO {

	@Value("${tms.status.backlog}")
	private String backlog;

	@Value("${tms.status.todo}")
	private String todo;

	@Value("${tms.status.closed}")
	private String closed;

	@Value("${tms.status.code_merged}")
	private String code_merged;

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Autowired
	private SessionFactory sessionFactory;

	// -------------------Create a Subtask---------------
	@Override
	public long createSubtask(SubtaskDTO subtaskDTO) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", backlog, TmsStatusMst.class);
		TmsStoryMst storyId = hibernateUtil.fetchById(subtaskDTO.getStoryId(), TmsStoryMst.class);
		TmsSubtask subtask = new TmsSubtask();
		BeanUtils.copyProperties(subtaskDTO, subtask);
		subtask.setTmsStoryMst(storyId);
		UserStoryStaus userStoryStatus = new UserStoryStaus();
		userStoryStatus.setCreatedDate(subtaskDTO.getCreatedDate());
		userStoryStatus.setTmsStatusMst(status);
		userStoryStatus.setType(subtaskDTO.getType());
		userStoryStatus.setTmsSubtask(subtask);
		subtask.getUserStoryStauses().add(userStoryStatus);
		return (Long)hibernateUtil.create(subtask);
	}

	//------------------- Update a Subtask --------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public long updateSubtaskStatus(SubtaskDTO subtaskDTO) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", subtaskDTO.getStatus(), TmsStatusMst.class);
		TmsUsers user = hibernateUtil.fetchById( subtaskDTO.getUserId(), TmsUsers.class);
		TmsSubtask tmsSubtask = hibernateUtil.fetchById(subtaskDTO.getSubtaskId(), TmsSubtask.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(subtaskDTO.getProjectId());
		UserStoryStaus previousStatus = getLatestStatus(tmsSubtask);
		UserStoryStaus userStoryStatus = new UserStoryStaus();
		userStoryStatus.setTmsStatusMst(status);
		userStoryStatus.setTmsSubtask(tmsSubtask);
		userStoryStatus.setType(tmsSubtask.getType());
		userStoryStatus.setModifiedDate(new Date());
		userStoryStatus.setTmsUsersByModifiedBy(user);
		if (!subtaskDTO.getStatus().equalsIgnoreCase(backlog) && previousStatus != null) {
			userStoryStatus.setTmsSprintMst(sprint);
			userStoryStatus.setTmsUsersByAssignedTo(previousStatus.getTmsUsersByAssignedTo());
			userStoryStatus.setAssignedDate(previousStatus.getAssignedDate());
		} 
		return (Long)hibernateUtil.create(userStoryStatus);
	}

	// -------------------Edit backlog Subtask---------------
	@SuppressWarnings("unchecked")
	@Override
	public void editSubtask(SubtaskDTO subtaskDTO) {
		TmsSubtask tmsSubtask = hibernateUtil.fetchById(subtaskDTO.getSubtaskId(), TmsSubtask.class);
		tmsSubtask.setJiraId(subtaskDTO.getJiraId());
		tmsSubtask.setCreatedDate(subtaskDTO.getCreatedDate());
		tmsSubtask.setEfforts(subtaskDTO.getEfforts());
		tmsSubtask.setScope(subtaskDTO.getScope());
		tmsSubtask.setType(subtaskDTO.getType());
		hibernateUtil.update(tmsSubtask);

	}

	//------------- Add to current Sprint ------------------------------------------------------------

	@Override
	public void addToCurrentSprint(List<SubtaskDTO> subtaskDTOs, Long projectId, Long assignToId, Long modifiedById) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			TmsStatusMst status = hibernateUtil.findByProperty(session, "status", todo, TmsStatusMst.class);
			TmsUsers assignedTo = (TmsUsers) session.get(TmsUsers.class, assignToId);//hibernateUtil.fetchById(assignToId, TmsUsers.class);
			TmsUsers modifiedBy = (TmsUsers) session.get(TmsUsers.class, modifiedById);//hibernateUtil.fetchById(modifiedById, TmsUsers.class);
			TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
			for (SubtaskDTO subtaskDTO : subtaskDTOs) {
				UserStoryStaus userStoryStatus = new UserStoryStaus();
				TmsSubtask tmsSubtask = (TmsSubtask) session.get(TmsSubtask.class, subtaskDTO.getSubtaskId());//hibernateUtil.fetchById(storyDTO.getStoryId(), TmsStoryMst.class);
				userStoryStatus.setTmsSprintMst(sprint);
				userStoryStatus.setTmsSubtask(tmsSubtask);
				userStoryStatus.setModifiedDate(new Date());
				userStoryStatus.setAssignedDate(new Date());
				userStoryStatus.setTmsUsersByAssignedTo(assignedTo);
				userStoryStatus.setTmsUsersByModifiedBy(modifiedBy);
				userStoryStatus.setTmsStatusMst(status);
				userStoryStatus.setType(tmsSubtask.getType());
				session.save(userStoryStatus);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			session = null;
			tx = null;
		}
	}

	@Override
	public List<TmsSubtask> getAllSubtasks() {
		return hibernateUtil.fetchAll(TmsSubtask.class);
	}

	@Override
	public TmsSubtask getSubtask(long id) {
		return hibernateUtil.fetchById(id, TmsSubtask.class);
	}

	@Override
	public Long getTotalEstimatedHoursBySprint(Long sprintId, Long userId) {
		Long efforts = (Long) hibernateUtil.getCurrentSession()
				.createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsSprintMst", "sprint")
				.createAlias("tmsSubtask", "sub")
				.createAlias("tmsUsersByAssignedTo", "users")
				.setProjection(Projections.sum("sub.efforts"))
				.add(Restrictions.eq("users.id", userId))
				.add(Restrictions.eq("sprint.sprintId", sprintId))
				.uniqueResult();
		return (efforts != null) ? efforts : 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception {

		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
					.add(Subqueries.propertyNotIn("sub.subtaskId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsSubtask", "sub")
							.add(Restrictions.eq("tsm.status", backlog))
							.setProjection(Property.forName("sub.subtaskId"))))
							.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(subtaskIds.size() > 0) {
				return parseSubtasks(getFilteredSubtasks(subtaskIds));
			} else {
				return null;
			}

		} else {
			throw new Exception("Sprint not found");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId) {

		List<Long> subtaskIds = getBackLogSubtaskIds();
		if(subtaskIds.size() > 0) {
			return parseSubtasks(getFilteredSubtasks(subtaskIds));
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Long> getBackLogSubtaskIds() {
		List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsSubtask", "sub")
				.createAlias("tmsStatusMst", "tsm")
				.add(Subqueries.propertyIn("uss.id",  DetachedCriteria.forClass(UserStoryStaus.class, "status")
						.add(Restrictions.eqProperty("uss.tmsSubtask", "status.tmsSubtask"))
						.setProjection(Projections.max("status.id"))))
						.add(Restrictions.eq("tsm.status", "BACKLOG"))
				.setProjection( Projections.distinct(Projections.property("sub.subtaskId"))).list();
		return subtaskIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(Long userId, Long projectId) throws Exception {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> subtaskIds = fetchCurrentUserSubtasksBySprint(userId, sprint.getSprintId());
			if(subtaskIds != null &&subtaskIds.size() > 0) {
				return parseSubtasks(getFilteredSubtasks(subtaskIds));
			} else {
				return null;
			}

		} else {
			throw new Exception("Sprint not found");
		}
	}

	public List fetchCurrentUserSubtasksBySprint(Long userId, Long sprintId) {
		List<Long> backLogIds = getBackLogSubtaskIds();
		return hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsSprintMst", "sprint")
				.createAlias("tmsSubtask", "sub")
				.createAlias("tmsUsersByAssignedTo", "users")
				.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
				.add(backLogIds.size() > 0 ? Restrictions.not(Restrictions.in("sub.subtaskId", backLogIds)): Restrictions.sqlRestriction("(1=1)"))
				.add(Restrictions.eq("users.id", userId))
				.add(Restrictions.eq("sprint.sprintId", sprintId)).list();
	}

	@SuppressWarnings("unchecked")
	private List<TmsSubtask> getFilteredSubtasks(List<Long> subtaskIds) {
		hibernateUtil.getCurrentSession().enableFilter(TmsSubtask.LATEST_STATUS_FILTER);
		List<TmsSubtask> subtasks = hibernateUtil.getCurrentSession().createCriteria(TmsSubtask.class)
				.add(Restrictions.in("subtaskId", subtaskIds)).list();
		hibernateUtil.getCurrentSession().disableFilter(TmsSubtask.LATEST_STATUS_FILTER);
		return subtasks;
	}

	private List<LinkedHashMap<String, Object>> parseSubtasks(List<TmsSubtask> subtasks) {

		List<LinkedHashMap<String, Object>> userSubtasks = new ArrayList<LinkedHashMap<String, Object>>();
		for (TmsSubtask tmsSubtask : subtasks) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("subtaskId", tmsSubtask.getSubtaskId());
			map.put("storyId", tmsSubtask.getTmsStoryMst().getStoryId());
			map.put("story", tmsSubtask.getTmsStoryMst().getJiraId());
			map.put("jiraId", tmsSubtask.getJiraId());
			map.put("scope", tmsSubtask.getScope());
			map.put("type", tmsSubtask.getType());
			map.put("efforts", tmsSubtask.getEfforts());
			map.put("createdDate", tmsSubtask.getCreatedDate());

			for (UserStoryStaus userStoryStaus : tmsSubtask.getUserStoryStauses()) {
				LinkedHashMap<String, Object> uss = new LinkedHashMap<String, Object>();
				uss.put("id", userStoryStaus.getId());
				uss.put("createdDate", userStoryStaus.getCreatedDate());
				uss.put("type", userStoryStaus.getType());
				uss.put("status", userStoryStaus.getTmsStatusMst().getStatus());
				if(userStoryStaus.getAssignedDate() != null) {
					uss.put("assignedDate", userStoryStaus.getAssignedDate());
				}
				if(userStoryStaus.getModifiedDate() != null) {
					uss.put("modifiedDate", userStoryStaus.getModifiedDate());
				}
				if(userStoryStaus.getTmsUsersByAssignedTo() != null) {
					uss.put("assignedTo", userStoryStaus.getTmsUsersByAssignedTo().getUserName());				
				}
				if(userStoryStaus.getTmsUsersByModifiedBy() != null) {
					uss.put("modifiedBy", userStoryStaus.getTmsUsersByModifiedBy().getUserName());				
				}
				map.put("userStoryStatus", uss);
			}

			userSubtasks.add(map);
		}
		return userSubtasks;
	}

	private UserStoryStaus getLatestStatus(TmsSubtask tmsSubtask) {
		List<UserStoryStaus> userStoryStatusList = new ArrayList<UserStoryStaus>();
		userStoryStatusList.addAll(tmsSubtask.getUserStoryStauses());
		Collections.sort(userStoryStatusList);
		return (UserStoryStaus) userStoryStatusList.get(userStoryStatusList.size() - 1);
	}

}

package com.cyb.tms.dao.impl;

import java.util.List;







import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsStoryDAOImpl implements TmsStoryDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public long createStory(StoryDTO storyDTO) {
		TmsUsers user = hibernateUtil.findByPropertyName("userName", storyDTO.getUser(), TmsUsers.class);
		TmsModule module = hibernateUtil.findByPropertyName("moduleName", storyDTO.getModule(), TmsModule.class);
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", storyDTO.getStatus(), TmsStatusMst.class);
		TmsStoryMst storyMst = new TmsStoryMst();
		BeanUtils.copyProperties(storyDTO, storyMst);
		storyMst.setTmsUsers(user);
		storyMst.setTmsModule(module);
		storyMst.setTmsStatusMst(status);
		return (Long)hibernateUtil.create(storyMst);
	}

	@Override
	public TmsStoryMst updateStory(TmsStoryMst story) {
		return hibernateUtil.update(story);
	}

	@Override
	public List<TmsStoryMst> getAllStories() {
		return hibernateUtil.fetchAll(TmsStoryMst.class);
	}

	@Override
	public TmsStoryMst getSprint(long id) {
		return hibernateUtil.fetchById(id, TmsStoryMst.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsStoryMst> getStoriesBySprint(String sprintName) throws Exception {
		
		TmsSprintMst sprint = hibernateUtil.findByPropertyName("sprintName", sprintName, TmsSprintMst.class);
		if(sprint != null) {
			Criteria criteria = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class, "story");
			criteria.createAlias("userStoryStatus", "uss");
			criteria.add(Restrictions.eqProperty("uss.jiraId", "story.jiraId"));
			criteria.add(Restrictions.eq("uss.tmsSprintMst", sprint.getSprintId()));
			return criteria.list();
			
		} else {
			throw new Exception("Sprint not found");
		}
	}

}

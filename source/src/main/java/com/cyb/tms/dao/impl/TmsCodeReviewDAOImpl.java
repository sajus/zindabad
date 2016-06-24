package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsCodeReviewDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsCodeReviewDAOImpl implements TmsCodeReviewDAO {
		
	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;
	
	
	@Override
	public long createReview(CodeReviewDTO codeReviewDTO) {
		return (Long)hibernateUtil.create(setDtoToDo(codeReviewDTO));
	}

	@Override
	public List<TmsCodeReview> getAllReviews() {
        return hibernateUtil.fetchAll(TmsCodeReview.class);
	}
	
	private TmsCodeReview setDtoToDo(CodeReviewDTO codeReviewDTO) {
   		TmsSubtask subtask = hibernateUtil.findByPropertyName("jiraId", codeReviewDTO.getJiraId(), TmsSubtask.class);
		TmsUsers developerId = hibernateUtil.fetchById( codeReviewDTO.getDeveloperId(), TmsUsers.class);
		TmsUsers fixedById = hibernateUtil.fetchById( codeReviewDTO.getFixedById(), TmsUsers.class);
		TmsUsers reviewerId = hibernateUtil.fetchById( codeReviewDTO.getReviewerId(), TmsUsers.class);
		TmsCodeReview review= new TmsCodeReview();
		review.setTmsSubtask(subtask);
		review.setTmsUsersByDeveloper(developerId);
		review.setTmsUsersByFixedBy(fixedById);
		review.setTmsUsersByReviewer(reviewerId);
		review.setCommentType(codeReviewDTO.getCommentType());
		review.setComments(codeReviewDTO.getComments());
		review.setCommentsFixedDate(codeReviewDTO.getCommentsFixedDate());
		review.setFileName(codeReviewDTO.getFileName());
		review.setPullRequest(codeReviewDTO.getPullRequest());
		review.setPullRequestDate(codeReviewDTO.getPullRequestDate());
		review.setReviewDate(codeReviewDTO.getReviewDate());
		review.setReviewerType(codeReviewDTO.getReviewerType());		
		return review;
	}

	// -------------------Edit Code review---------------
		@SuppressWarnings("unchecked")
	    @Override
	    public void editCodeReview(CodeReviewDTO codeReviewDTO) {
			TmsCodeReview tmsCodeReview = hibernateUtil.fetchById(codeReviewDTO.getReviewId(), TmsCodeReview.class);
			TmsUsers user = hibernateUtil.findByPropertyName("userName", codeReviewDTO.getFixedByName(), TmsUsers.class);
			tmsCodeReview.setPullRequest(codeReviewDTO.getPullRequest());
			tmsCodeReview.setPullRequestDate(codeReviewDTO.getPullRequestDate());
			tmsCodeReview.setFileName(codeReviewDTO.getFileName());
			tmsCodeReview.setComments(codeReviewDTO.getComments());
			tmsCodeReview.setCommentsFixedDate(codeReviewDTO.getCommentsFixedDate());
			tmsCodeReview.setTmsUsersByFixedBy(user);
	        hibernateUtil.update(tmsCodeReview);   
	    }
			
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getCodeReviewForCureentUserBySprint(Long userId, Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<TmsCodeReview> tmsCodeReview = hibernateUtil.getCurrentSession().createCriteria(TmsCodeReview.class)
					.createAlias("tmsSubtask", "subtask")
					.add(Subqueries.propertyIn("subtask.subtaskId", DetachedCriteria.forClass(UserStoryStaus.class, "uss")
							.createAlias("tmsSprintMst", "sprint")
							.createAlias("tmsSubtask", "sub")
							.createAlias("tmsUsersByAssignedTo", "users")
							.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
							.add(Restrictions.eq("users.id", userId))
							.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())))).list();
			if(tmsCodeReview != null && tmsCodeReview.size() > 0) {
				return parseCodeReview(tmsCodeReview);			
			}
		}
		return null;	
	}
	

	private List<LinkedHashMap<String, Object>> parseCodeReview(List<TmsCodeReview> codeReviews) {

		List<LinkedHashMap<String, Object>> userCodeReviews = new ArrayList<LinkedHashMap<String, Object>>();
		for (TmsCodeReview tmsCodeReview : codeReviews) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("reviewId", tmsCodeReview.getReviewId());
			map.put("jiraId", tmsCodeReview.getTmsSubtask().getJiraId());
			map.put("reviewerType", tmsCodeReview.getReviewerType());
			map.put("developerName", tmsCodeReview.getTmsUsersByDeveloper().getUserName());
			map.put("reviewerName", tmsCodeReview.getTmsUsersByReviewer().getUserName());
			map.put("pullRequest", tmsCodeReview.getPullRequest());
			map.put("pullRequestDate", tmsCodeReview.getPullRequestDate());
			map.put("reviewDate", tmsCodeReview.getReviewDate());
			map.put("fileName", tmsCodeReview.getFileName());
			map.put("comments", tmsCodeReview.getComments());
			map.put("commentsFixedDate", tmsCodeReview.getCommentsFixedDate());
			map.put("fixedByName", tmsCodeReview.getTmsUsersByFixedBy().getUserName());
			map.put("commentType", tmsCodeReview.getCommentType());
		    userCodeReviews.add(map);
			}
		return userCodeReviews;
	}	
}

package com.cyb.tms.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsCodeReviewDAO;
import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsCodeReviewDAOImpl implements TmsCodeReviewDAO {
	
	
	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Autowired
	private HibernateUtil hibernateUtil;
	

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
		TmsUsers tmsUsersByDeveloper = hibernateUtil.fetchById( codeReviewDTO.getTmsUsersByDeveloper(), TmsUsers.class);
		TmsUsers tmsUsersByFixedBy = hibernateUtil.fetchById( codeReviewDTO.getTmsUsersByFixedBy(), TmsUsers.class);
		TmsUsers reviewer = hibernateUtil.fetchById( codeReviewDTO.getReviewer(), TmsUsers.class);
		
		TmsCodeReview review= new TmsCodeReview();
		review.setTmsSubtask(subtask);
		review.setTmsUsersByDeveloper(tmsUsersByDeveloper);
		review.setTmsUsersByFixedBy(tmsUsersByFixedBy);
		review.setTmsUsersByReviewer(reviewer);
		review.setId(codeReviewDTO.getId());
		review.setCommentType(codeReviewDTO.getCommentType());
		review.setComments(codeReviewDTO.getComments());
		review.setCommentsFixedDate(new Date());
		review.setFileName(codeReviewDTO.getFileName());
		review.setPullRequest(codeReviewDTO.getPullRequest());
		review.setReviewDate(new Date());
		review.setReviewerType(codeReviewDTO.getReviewerType());
					
		return review;
	}
}

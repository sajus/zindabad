package com.cyb.tms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.entity.TmsSprintMst;

public interface TmsCodeReviewDAO {
	
	public long createReview(CodeReviewDTO  codeReviewDTO);
	public List<TmsCodeReview> getAllReviews();
	public void editCodeReview(CodeReviewDTO  codeReviewDTO);
	List<LinkedHashMap<String, Object>> getCodeReviewForCureentUserBySprint(Long userId, Long projectId) throws Exception;
}

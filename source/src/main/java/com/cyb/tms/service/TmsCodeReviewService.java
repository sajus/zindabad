package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;

public interface TmsCodeReviewService {
	
	public long createReview(CodeReviewDTO  codeReviewDTO);
	public List<TmsCodeReview> getAllReviews();
	public void editCodeReview(CodeReviewDTO  codeReviewDTO);
	List<LinkedHashMap<String, Object>> getCodeReviewForCureentUserBySprint(Long userId, Long projectId) throws Exception;

	
}

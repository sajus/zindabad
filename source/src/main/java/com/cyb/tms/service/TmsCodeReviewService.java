package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;

public interface TmsCodeReviewService {
	
	public long createReview(CodeReviewDTO  codeReviewDTO);
	public List<TmsCodeReview> getAllReviews();

}

package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;

public interface TmsCodeReviewDAO {
	
	public long createReview(CodeReviewDTO  codeReviewDTO);
		public List<TmsCodeReview> getAllReviews();
	
}

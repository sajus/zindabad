package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsCodeReviewDAO;
import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.service.TmsCodeReviewService;

@Service
@Transactional
public class TmsCodeReviewServiceImpl implements TmsCodeReviewService {

	@Autowired
	private TmsCodeReviewDAO tmsCodeReviewDAO;
	
	@Override
	public long createReview(CodeReviewDTO codeReviewDTO) {
		return tmsCodeReviewDAO.createReview(codeReviewDTO);
	}

	@Override
	public List<TmsCodeReview> getAllReviews() {
		return tmsCodeReviewDAO.getAllReviews();
	}

	@Override
	public void editCodeReview(CodeReviewDTO codeReviewDTO) {
		tmsCodeReviewDAO.editCodeReview(codeReviewDTO);
		
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCodeReviewForCureentUserBySprint(Long userId, Long projectId)
			throws Exception {
		
		return tmsCodeReviewDAO.getCodeReviewForCureentUserBySprint(userId, projectId);
	}
}

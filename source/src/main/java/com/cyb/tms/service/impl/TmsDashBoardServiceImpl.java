package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsDashBoardDAO;
import com.cyb.tms.service.TmsDashBoardService;

@Transactional
@Service
public class TmsDashBoardServiceImpl implements TmsDashBoardService {
	
	@Autowired
	private TmsDashBoardDAO tmsDashBoardDAO;

	@Override
	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId,
			Long projectId) {
		return tmsDashBoardDAO.getUserDashBoardData(userId, projectId);
	}

}

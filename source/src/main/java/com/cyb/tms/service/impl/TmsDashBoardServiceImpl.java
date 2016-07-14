package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsDashBoardDAO;
import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;
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

	@Override
	public List<LinkedHashMap<Object, Object>> getManagerDashBoardData(Long projectId) {
		return tmsDashBoardDAO.getManagerDashBoardData(projectId);
	}

}

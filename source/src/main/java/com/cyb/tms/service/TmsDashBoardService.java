package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;

public interface TmsDashBoardService {

	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId, Long projectId);
	public List<LinkedHashMap<Object, Object>> getManagerDashBoardData(Long projectId);
 
}

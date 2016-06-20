package com.cyb.tms.dao;

import java.util.LinkedHashMap;

public interface TmsDashBoardDAO {
	
	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId , Long projectId);
}

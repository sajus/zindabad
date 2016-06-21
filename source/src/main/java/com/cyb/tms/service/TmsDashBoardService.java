package com.cyb.tms.service;

import java.util.LinkedHashMap;

public interface TmsDashBoardService {

	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId, Long projectId);
}

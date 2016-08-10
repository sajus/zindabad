package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.entity.TmsStatusMst;

public interface TmsStatusService {

	public TmsStatusMst getStatusByName(String status);
	public TmsStatusMst getStatus(Long  statusId);
	public List<TmsStatusMst> getAllStatus();
	
}

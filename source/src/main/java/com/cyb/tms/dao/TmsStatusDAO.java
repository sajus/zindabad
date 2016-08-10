package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.entity.TmsStatusMst;

public interface TmsStatusDAO {
	
	public TmsStatusMst getStatusByName(String status);
	public TmsStatusMst getStatus(Long  statusId);
	public List<TmsStatusMst> getAllStatus();
}

package com.cyb.tms.dao;

import com.cyb.tms.entity.TmsStatusMst;

public interface TmsStatusDAO {
	
	public TmsStatusMst getStatusByName(String status);

}

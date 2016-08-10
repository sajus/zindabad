package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsStatusDAO;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.service.TmsStatusService;

@Transactional
@Service
public class TmsStatusSeviceImpl implements TmsStatusService{

	@Autowired
	TmsStatusDAO tmsStatusDAO;
	
	@Override
	public TmsStatusMst getStatusByName(String status) {
		return tmsStatusDAO.getStatusByName(status);
	}

	@Override
	public TmsStatusMst getStatus(Long statusId) {
		return tmsStatusDAO.getStatus(statusId);
	}

	@Override
	public List<TmsStatusMst> getAllStatus() {
		return tmsStatusDAO.getAllStatus();
	}
	

}

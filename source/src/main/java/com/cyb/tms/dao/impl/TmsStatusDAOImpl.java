package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsStatusDAO;
import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsStatusDAOImpl implements TmsStatusDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public TmsStatusMst getStatusByName(String status) {
		return hibernateUtil.findByPropertyName("status", status, TmsStatusMst.class);
	}

	@Override
	public TmsStatusMst getStatus(Long statusId) {
		return hibernateUtil.fetchById(statusId, TmsStatusMst.class);
	}

	@Override
	public List<TmsStatusMst> getAllStatus() {
		return hibernateUtil.fetchAll(TmsStatusMst.class);
	}

}

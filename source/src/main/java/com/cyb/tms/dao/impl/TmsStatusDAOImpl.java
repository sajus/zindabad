package com.cyb.tms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsStatusDAO;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsStatusDAOImpl implements TmsStatusDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public TmsStatusMst getStatusByName(String status) {
		return hibernateUtil.findByPropertyName("status", status, TmsStatusMst.class);
	}

}

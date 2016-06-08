package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.service.TmsLeaveService;

@Service("tmsLeaveService")
@Transactional
public class TmsLeaveServiceImpl implements TmsLeaveService{

	@Autowired
	TmsLeaveDAO tmsLeaveDAO;

	@Override
	public long createLeave(TmsLeaveDTO tmsleaveDTO) {
		return tmsLeaveDAO.createLeave(tmsleaveDTO);
	}

	@Override
	public TmsLeaveMst updateLeave(TmsLeaveDTO tmsleaveDTO) {
		return tmsLeaveDAO.updateLeave(tmsleaveDTO);
	}

	@Override
	public void deleteLeave(long id) {
		tmsLeaveDAO.deleteLeave(id);
	}

	@Override
	public List<TmsLeaveMst> getAllLeaves() {
		return tmsLeaveDAO.getAllLeaves();
	}

	@Override
	public TmsLeaveMst getLeave(long id) {
		return tmsLeaveDAO.getLeave(id);
	}
	
	@Override
	public List<TmsLeaveMst> getLeaveBySprint(Long projectId) throws Exception {
		return tmsLeaveDAO.getLeaveBySprint(projectId);
	}
}

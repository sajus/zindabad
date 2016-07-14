package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;


public interface TmsLeaveDAO {

	public long createLeave(TmsLeaveDTO tmsleaveDTO);
    public TmsLeaveMst updateLeave(TmsLeaveDTO tmsleaveDTO);
    public void deleteLeave(Long id);
    public List<TmsLeaveMst> getAllLeavesBySprint(Long projectId) throws Exception;
    public TmsLeaveMst getLeave(long id);
    public List<TmsLeaveMst> getCurrentUserLeavesBySprint(Long userId, Long projectId) throws Exception;
    public int getTotalLeavesBySprint(Long projectId);
	public int calculateUserLeavesTotalBySprint(Long userId, Long sprintId);
}

package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;

public interface TmsLeaveService {

	public long createLeave(TmsLeaveDTO tmsleaveDTO);
    public TmsLeaveMst updateLeave(TmsLeaveDTO tmsleaveDTO);
    public void deleteLeave(long id);
    public List<TmsLeaveMst> getAllLeaves();
    public TmsLeaveMst getLeave(long id);
    public List<TmsLeaveMst> getLeaveBySprint(Long projectId) throws Exception;
}

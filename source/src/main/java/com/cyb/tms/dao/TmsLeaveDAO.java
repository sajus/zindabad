package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.entity.TmsLeaveMst;


public interface TmsLeaveDAO {

	public long createLeave(TmsLeaveMst leave);
    public TmsLeaveMst updateLeave(TmsLeaveMst leave);
    public void deleteLeave(long id);
    public List<TmsLeaveMst> getAllLeaves();
    public TmsLeaveMst getLeave(long id);
    public List<TmsLeaveMst> getLeaveBySprint(Long projectId) throws Exception;
}

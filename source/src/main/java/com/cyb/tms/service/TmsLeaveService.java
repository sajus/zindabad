package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.entity.TmsLeaveMst;

public interface TmsLeaveService {

	public long createLeave(TmsLeaveMst leave);
    public TmsLeaveMst updateLeave(TmsLeaveMst leave);
    public void deleteLeave(long id);
    public List<TmsLeaveMst> getAllLeaves();
    public TmsLeaveMst getLeave(long id);
    public List<TmsLeaveMst> getLeaveBySprint(Long projectId) throws Exception;
}
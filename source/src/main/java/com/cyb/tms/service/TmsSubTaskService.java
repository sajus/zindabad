package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.entity.TmsSubtask;

public interface TmsSubTaskService {
	
	public long createSubask(TmsSubtask subtask);
	public TmsSubtask updateSubtask(TmsSubtask subtask);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);

}

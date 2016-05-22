package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.entity.TmsSubtask;

public interface TmsSubTaskDAO {

	public long createSubtask(TmsSubtask subtask);
	public TmsSubtask updateSubtask(TmsSubtask subtask);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
}

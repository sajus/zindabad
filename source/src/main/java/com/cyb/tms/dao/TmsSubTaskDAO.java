package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;

public interface TmsSubTaskDAO {

	public long createSubtask(TmsSubtask subtask);
	public TmsSubtask updateSubtask(TmsSubtask subtask);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
	public List<TmsSubtask> getSubTaskBySprint(Long projectId) throws Exception;
	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception;
}

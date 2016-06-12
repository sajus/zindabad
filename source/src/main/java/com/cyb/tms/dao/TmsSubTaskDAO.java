package com.cyb.tms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;

public interface TmsSubTaskDAO {

	public long createSubtask(TmsSubtask subtask);
	public TmsSubtask updateSubtask(TmsSubtask subtask);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(Long userId, Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId);
}

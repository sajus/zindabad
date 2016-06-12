package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.entity.TmsSubtask;

public interface TmsSubTaskService {
	
	public long createSubask(TmsSubtask subtask);
	public TmsSubtask updateSubtask(TmsSubtask subtask);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception;
	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId);
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(
			Long userId, Long projectId)throws Exception;
	
}

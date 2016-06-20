package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;

public interface TmsSubTaskService {
	
	public long createSubtask(SubtaskDTO subtaskDTO);
	public long updateSubtask(SubtaskDTO subtaskDTO);
	public List<String> editSubtask(SubtaskDTO subtaskDTO);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception;
	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId);
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(
			Long userId, Long projectId)throws Exception;
	void addToCurrentSprint(List<SubtaskDTO> subtaskDTOs, Long projectId, Long assignToId, Long modifiedById);
}

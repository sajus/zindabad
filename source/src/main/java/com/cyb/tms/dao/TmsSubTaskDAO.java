package com.cyb.tms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;

public interface TmsSubTaskDAO {

	public long createSubtask(SubtaskDTO subtaskDTO);
	public long updateSubtaskStatus(SubtaskDTO subtaskDTO);
	public void editSubtask(SubtaskDTO subtaskDTO);
	public List<TmsSubtask> getAllSubtasks();
	public TmsSubtask getSubtask(long id);
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(Long userId, Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId);
	public void addToCurrentSprint(List<SubtaskDTO> subtaskDTOs, Long projectId, Long assignToId, Long modifiedById);
	public Long getTotalEstimatedHoursBySprint(Long sprintId, Long userId);
	public List<LinkedHashMap<String, Object>> fetchSubtasksByStoryId(Long storyId);
}

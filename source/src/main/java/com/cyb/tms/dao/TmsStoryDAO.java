package com.cyb.tms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsStoryMst;

public interface TmsStoryDAO {

	public long createStory(StoryDTO storyDTO);
	public long updateStory(StoryDTO storyDTO);
	public List<String> editStory(StoryDTO storyDTO);
	public List<TmsStoryMst> getAllStories();
	public TmsStoryMst getSprint(long id);
	public List<LinkedHashMap<String, Object>> getStoriesBySprint(Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogStories(Long projectId) throws Exception;
	List<String> getIncompleteStoriesInSprint(Long projectId);
	public List<LinkedHashMap<String, Object>> getCurrentUserStoriesBySprint(
			Long userId, Long projectId);
	void addToCurrentSprint(List<StoryDTO> storyDTOs, Long projectId, Long assignToId, Long modifiedById);
			
}

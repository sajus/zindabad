package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsStoryMst;

public interface TmsStoryService {
	
	public long createStory(StoryDTO storyDTO);
	public void updateStoryStatus(StoryDTO storyDTO);
	public void editStory(StoryDTO storyDTO);
	public boolean isStoryExist(String jiraId);
	public List<TmsStoryMst> getAllStories();
	public TmsStoryMst getSprint(long id);
	public List<LinkedHashMap<String, Object>> getStoriesBySprint(Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getBackLogStories(Long projectId) throws Exception;
	public List<LinkedHashMap<String, Object>> getCurrentUserStoriesBySprint(
			Long userId, Long projectId);
	void addToCurrentSprint(List<StoryDTO> storyDTOs, Long projectId, Long assignToId, Long modifiedById);
	public List<LinkedHashMap<String, Object>> getAllCurrentUserStoriesBySprint(
			Long projectId);	
}

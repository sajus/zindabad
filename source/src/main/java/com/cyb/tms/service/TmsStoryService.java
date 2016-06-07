package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsStoryMst;

public interface TmsStoryService {
	
	public long createStory(StoryDTO storyDTO);
	public TmsStoryMst updateStory(TmsStoryMst story);
	public List<TmsStoryMst> getAllStories();
	public TmsStoryMst getSprint(long id);
	public List<LinkedHashMap<String, Object>> getStoriesBySprint(Long projectId) throws Exception;
	public void addToCurrentSprint(StoryDTO storyDTO); 
	public List<LinkedHashMap<Object, Object>> getBackLogStories(Long projectId) throws Exception;

}

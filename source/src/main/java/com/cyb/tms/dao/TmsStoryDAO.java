package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsStoryMst;

public interface TmsStoryDAO {

	public long createStory(StoryDTO storyDTO);
	public TmsStoryMst updateStory(TmsStoryMst story);
	public List<TmsStoryMst> getAllStories();
	public TmsStoryMst getSprint(long id);
	public List<TmsStoryMst> getStoriesBySprint(Long projectId) throws Exception;
}

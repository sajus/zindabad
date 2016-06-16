package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.service.TmsStoryService;

@Service
@Transactional
public class TmsStoryServiceImpl implements TmsStoryService {

	@Autowired
	private TmsStoryDAO tmsStoryDAO;

	@Override
	public long createStory(StoryDTO storyDTO) {
		return tmsStoryDAO.createStory(storyDTO);
	}

	@Override
	public long updateStory(StoryDTO storyDTO) {
		return tmsStoryDAO.updateStory(storyDTO);
	}

	@Override
	public List<TmsStoryMst> getAllStories() {
		return tmsStoryDAO.getAllStories();
	}

	@Override
	public TmsStoryMst getSprint(long id) {
		return tmsStoryDAO.getSprint(id);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getStoriesBySprint(Long projectId) throws Exception {
		return tmsStoryDAO.getStoriesBySprint(projectId);
	}

	@Override
	public void addToCurrentSprint(StoryDTO storyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LinkedHashMap<String, Object>> getBackLogStories(Long projectId)throws Exception {
		return tmsStoryDAO.getBackLogStories(projectId);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserStoriesBySprint(
			Long userId, Long projectId) {
		return tmsStoryDAO.getCurrentUserStoriesBySprint(userId, projectId);
	}
	
	
}

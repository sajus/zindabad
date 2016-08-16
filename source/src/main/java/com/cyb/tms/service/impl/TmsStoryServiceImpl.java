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
	public void updateStoryStatus(StoryDTO storyDTO) {
		tmsStoryDAO.updateStoryStatus(storyDTO);
	}
	
	@Override
	public void editStory(StoryDTO storyDTO) {
		 tmsStoryDAO.editStory(storyDTO);
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
	public List<LinkedHashMap<String, Object>> getBackLogStories(Long projectId)throws Exception {
		return tmsStoryDAO.getBackLogStories(projectId);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserStoriesBySprint(
			Long userId, Long projectId) {
		return tmsStoryDAO.getCurrentUserStoriesBySprint(userId, projectId);
	}

	@Override
	public void addToCurrentSprint(List<StoryDTO> storyDTOs, Long projectId, Long assignToId, Long modifiedById) {
		tmsStoryDAO.addToCurrentSprint(storyDTOs, projectId, assignToId, modifiedById);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getAllCurrentUserStoriesBySprint(Long projectId) {
		return tmsStoryDAO.getAllCurrentUserStoriesBySprint(projectId);
	}
	
	@Override
	public boolean isStoryExist(String jiraId) {
		return tmsStoryDAO.findByJiraId(jiraId)!=null;
	}
	
	public StoryDTO findByJiraId(String jiraId) {
		StoryDTO storyDto = new StoryDTO();
		TmsStoryMst story = tmsStoryDAO.findByJiraId(jiraId);
		createStory(storyDto);
		return storyDto;
	}
}

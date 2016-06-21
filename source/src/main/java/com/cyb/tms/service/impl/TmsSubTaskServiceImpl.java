package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.service.TmsSubTaskService;

@Transactional
@Service
public class TmsSubTaskServiceImpl implements TmsSubTaskService {
	
	
	@Autowired
	private TmsSubTaskDAO tmsSubTaskDAO;

	@Override
	public long createSubtask(SubtaskDTO subtaskDTO)  {
		return tmsSubTaskDAO.createSubtask(subtaskDTO);
	}

	@Override
	public long updateSubtaskStatus(SubtaskDTO subtaskDTO) {
		return tmsSubTaskDAO.updateSubtaskStatus(subtaskDTO);
	}
	
	@Override
	public void editSubtask(SubtaskDTO subtaskDTO) {
		 tmsSubTaskDAO.editSubtask(subtaskDTO);
	}

	@Override
	public List<TmsSubtask> getAllSubtasks() {
		return tmsSubTaskDAO.getAllSubtasks();
	}

	@Override
	public TmsSubtask getSubtask(long id) {
		return tmsSubTaskDAO.getSubtask(id);
	}

	@Override
	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(
			Long projectId) throws Exception {
		return tmsSubTaskDAO.getSubtasksBySprint(projectId);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId) {
		return tmsSubTaskDAO.getBackLogSubtasks(projectId);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(
			Long userId, Long projectId) throws Exception {
		return tmsSubTaskDAO.getCurrentUserSubTasksBySprintBy(userId, projectId);
	}

	@Override
	public void addToCurrentSprint(List<SubtaskDTO> subtaskDTOs, Long projectId, Long assignToId, Long modifiedById) {
		tmsSubTaskDAO.addToCurrentSprint(subtaskDTOs, projectId, assignToId, modifiedById);
		
	}


}

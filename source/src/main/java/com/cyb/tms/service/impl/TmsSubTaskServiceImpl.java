package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSubtask;
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
	public TmsSubtask updateSubtask(TmsSubtask subtask) {
		return tmsSubTaskDAO.updateSubtask(subtask);
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

}

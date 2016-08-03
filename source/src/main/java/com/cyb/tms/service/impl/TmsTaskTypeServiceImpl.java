package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsTaskTypeDAO;
import com.cyb.tms.dto.TmsTaskTypeDTO;
import com.cyb.tms.entity.TmsTaskType;
import com.cyb.tms.service.TmsTaskTypeService;

@Transactional
@Service
public class TmsTaskTypeServiceImpl implements TmsTaskTypeService {

	@Autowired
	private TmsTaskTypeDAO tmsTaskTypeDAO;

	@Override
	public long createTaskType(TmsTaskType taskType) {
		return tmsTaskTypeDAO.createTaskType(taskType);
	}

	@Override
	public TmsTaskType getTaskType(Long taskTypeId) {
		return tmsTaskTypeDAO.getTaskType(taskTypeId);
	}

	@Override
	public void editTaskType(TmsTaskTypeDTO taskTypeDTO) {
		tmsTaskTypeDAO.editTaskType(taskTypeDTO);

	}

	@Override
	public List<TmsTaskTypeDTO> getAllTaskTypes() {
		return tmsTaskTypeDAO.getAllTaskTypes();
	}

}
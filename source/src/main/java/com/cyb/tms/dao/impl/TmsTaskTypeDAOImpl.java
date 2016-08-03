package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsTaskTypeDAO;
import com.cyb.tms.dto.TmsTaskTypeDTO;
import com.cyb.tms.entity.TmsTaskType;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsTaskTypeDAOImpl implements TmsTaskTypeDAO {

	@Autowired
    private HibernateUtil hibernateUtil;
	
	@Override
	public long createTaskType(TmsTaskType taskType) {
		return (long) hibernateUtil.create(taskType);
	}

	@Override
	public TmsTaskType getTaskType(Long taskTypeId) {
		return hibernateUtil.fetchById(taskTypeId, TmsTaskType.class);
	}

	// -------------------Edit TaskType---------------
	
	@SuppressWarnings("unchecked")
	@Override
	public void editTaskType(TmsTaskTypeDTO taskTypeDTO) {
		TmsTaskType tmsTaskType = hibernateUtil.fetchById(taskTypeDTO.getId(), TmsTaskType.class);
		tmsTaskType.setTaskTypeName(taskTypeDTO.getTaskTypeName());
		tmsTaskType.setTaskTypeDescription(taskTypeDTO.getTaskTypeDescription());
	    hibernateUtil.update(tmsTaskType); 
	}

	@Override
	public List<TmsTaskTypeDTO> getAllTaskTypes() {
		List<TmsTaskTypeDTO> tmsTaskTypeDTOs = new ArrayList<TmsTaskTypeDTO>();
				
		for (TmsTaskType tmsTaskType : hibernateUtil.fetchAll(TmsTaskType.class)) {
			tmsTaskTypeDTOs.add(constructTmsTaskTypeDTO(tmsTaskType));
		}
		return tmsTaskTypeDTOs;
	}
	
	private TmsTaskTypeDTO constructTmsTaskTypeDTO(TmsTaskType tmsTaskType) {
		TmsTaskTypeDTO taskTypeDTO = new TmsTaskTypeDTO();
		BeanUtils.copyProperties(tmsTaskType, taskTypeDTO);
		return taskTypeDTO;
	}

}

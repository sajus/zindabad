package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsTaskTypeDTO;
import com.cyb.tms.entity.TmsTaskType;

public interface TmsTaskTypeDAO {

	public long createTaskType(TmsTaskType taskType);
	public TmsTaskType getTaskType(Long  taskTypeId);
	public void editTaskType(TmsTaskTypeDTO taskTypeDTO);
	public List<TmsTaskTypeDTO> getAllTaskTypes();
}

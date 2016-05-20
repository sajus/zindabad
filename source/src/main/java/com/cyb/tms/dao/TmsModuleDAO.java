package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;

public interface TmsModuleDAO {
	
	public long createModule(TmsModule module);
	public TmsModule getModule(Long  mouduleId);
	public List<TmsModuleDTO> getAllModules();

}

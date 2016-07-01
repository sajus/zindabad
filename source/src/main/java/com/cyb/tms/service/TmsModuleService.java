package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;

public interface TmsModuleService {
	
	public long createModule(TmsModule module);
	public TmsModule getModule(Long  mouduleId);
	public void editModule(TmsModuleDTO moduleDTO);
	public List<TmsModuleDTO> getAllModules();
	
}

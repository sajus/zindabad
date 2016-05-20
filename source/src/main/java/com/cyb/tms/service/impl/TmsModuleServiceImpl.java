package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsModuleDAO;
import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.service.TmsModuleService;

@Transactional
@Service
public class TmsModuleServiceImpl implements TmsModuleService {
	
	@Autowired
	private TmsModuleDAO tmsModuleDAO;

	@Override
	public long createModule(TmsModule module) {
		return tmsModuleDAO.createModule(module);
	}

	@Override
	public TmsModule getModule(Long mouduleId) {
		return tmsModuleDAO.getModule(mouduleId);
	}

	@Override
	public List<TmsModuleDTO> getAllModules() {
		return tmsModuleDAO.getAllModules();
	}

}

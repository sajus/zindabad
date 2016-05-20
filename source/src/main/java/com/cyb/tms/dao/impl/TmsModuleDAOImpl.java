package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsModuleDAO;
import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsModuleDAOImpl implements TmsModuleDAO {

	@Autowired
    private HibernateUtil hibernateUtil;
	
	@Override
	public long createModule(TmsModule module) {
		return (long) hibernateUtil.create(module);
	}

	@Override
	public TmsModule getModule(Long mouduleId) {
		return hibernateUtil.fetchById(mouduleId, TmsModule.class);
	}

	@Override
	public List<TmsModuleDTO> getAllModules() {
		List<TmsModuleDTO> tmsModuleDTOs = new ArrayList<TmsModuleDTO>();
		
		for (TmsModule tmsModule : hibernateUtil.fetchAll(TmsModule.class)) {
			tmsModuleDTOs.add(constructTmsModuleDTO(tmsModule));
		}
		return tmsModuleDTOs;
	}

	private TmsModuleDTO constructTmsModuleDTO(TmsModule tmsModule) {
		TmsModuleDTO tmsModuleDTO = new TmsModuleDTO();
		BeanUtils.copyProperties(tmsModule, tmsModuleDTO);
		return tmsModuleDTO;
	}


}

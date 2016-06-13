package com.cyb.tms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.service.TmsEffortsService;

@Transactional
@Service
public class TmsEffortsServiceImpl implements TmsEffortsService {

	@Autowired
	private TmsEffortsDAO tmsEffortsDAO;
	
	@Override
	public long createEffort(TmsEfforts effort) {
		return tmsEffortsDAO.createEffort(effort);
	}

	@Override
	public TmsEfforts updateEffort(TmsEfforts effort) {
		return tmsEffortsDAO.updateEffort(effort);
	}

	@Override
	public List<TmsEfforts> getAllEfforts() {
		return tmsEffortsDAO.getAllEfforts();
	}

	@Override
	public TmsEfforts getEffort(long id) {
		return tmsEffortsDAO.getEffort(id);
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserEffortsBySprint(Long userId, Long projectId) {
		return tmsEffortsDAO.getCurrentUserEffortsBySprint(userId, projectId);
	}

}

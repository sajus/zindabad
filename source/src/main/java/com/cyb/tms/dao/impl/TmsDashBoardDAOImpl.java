package com.cyb.tms.dao.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsDashBoardDAO;
import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.util.HibernateUtil;
import com.cyb.tms.util.WorkingDaysCalculator;

@Repository
public class TmsDashBoardDAOImpl implements TmsDashBoardDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;
	
	@Autowired
	private TmsSubTaskDAO tmsSubTaskDAO;
	
	@Autowired
	private TmsLeaveDAO tmsLeaveDAO;
	
	@Value("${tms.workinghours}")
	private String workingHours;

	@Override
	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId,
			Long projectId) {
		LinkedHashMap<Object, Object> dashBoard = new LinkedHashMap<Object, Object>();
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		dashBoard.put("sprintCapacity", sprint.getSprintHours());
		dashBoard.put("userCapacity", getCurrentUserCapacity(sprint, userId));
		dashBoard.put("totalEstHours", getEstimatedHours(sprint.getSprintId(), userId));
		dashBoard.put("hoursConsumed", getConsumedHours(sprint.getSprintHours(), sprint.getSprintStartDate()));
		return dashBoard;
	}

	private Long getCurrentUserCapacity(TmsSprintMst sprint, Long userId) {
		int days = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(sprint.getSprintStartDate(), sprint.getSprintEndDate());
		int leaves = tmsLeaveDAO.calculateUserLeavesTotalBySprint(userId, sprint.getSprintId());	
		return (long) ((days * Integer.parseInt(workingHours)) - leaves);
	}

	private Long getEstimatedHours(Long sprintId, Long userId) {
		return tmsSubTaskDAO.getTotalEstimatedHoursBySprint(sprintId, userId);
	}

	private int getConsumedHours(int sprintHours, Date sprintStartDate) {
		int days = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(sprintStartDate, new Date());
		return sprintHours - (days * Integer.parseInt(workingHours));
	}

}

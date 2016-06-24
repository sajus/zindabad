package com.cyb.tms.dao.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsDashBoardDAO;
import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsOrgLeavesDAO;
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
	
	@Autowired
	private TmsOrgLeavesDAO tmsOrgLeavesDAO;
	
	@Autowired
	private TmsEffortsDAO tmsEffortsDAO;
	
	@Value("${tms.workinghours}")
	private String workingHours;

	@Override
	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId,
			Long projectId) {
		LinkedHashMap<Object, Object> dashBoard = new LinkedHashMap<Object, Object>();
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		dashBoard.put("capacity", calculateCapacity(sprint));
		Long sprintCapacity = calculateSprintCapacity(sprint, userId);
		dashBoard.put("sprintCapacity", sprintCapacity);
		//dashBoard.put("totalEstHours", getEstimatedHours(sprint.getSprintId(), userId));
		int hoursConsumed = getConsumedHours(sprint.getSprintStartDate());
		dashBoard.put("hoursConsumed", hoursConsumed);
		dashBoard.put("hoursBurned", getTotalLoggedHours(userId, projectId));
		dashBoard.put("remainingHours", (sprintCapacity - hoursConsumed));
		return dashBoard;
	}

	private Double getTotalLoggedHours(Long userId, Long projectId) {
		return tmsEffortsDAO.getTotalHoursLoggedByUser(userId, projectId);
	}

	private Long calculateCapacity(TmsSprintMst sprint) {
		int days = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(sprint.getSprintStartDate(), sprint.getSprintEndDate());
		int holidays = tmsOrgLeavesDAO.calculateTotalHolidays(sprint.getSprintStartDate(), sprint.getSprintEndDate());
		return (long) ((days - holidays) * Integer.parseInt(workingHours));
	}

	private Long calculateSprintCapacity(TmsSprintMst sprint, Long userId) {
		int days = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(sprint.getSprintStartDate(), sprint.getSprintEndDate());
		int leaves = tmsLeaveDAO.calculateUserLeavesTotalBySprint(userId, sprint.getSprintId());	
		int holidays = tmsOrgLeavesDAO.calculateTotalHolidays(sprint.getSprintStartDate(), sprint.getSprintEndDate());
		return (long) ((days - (holidays + leaves)) * Integer.parseInt(workingHours));
	}

	private Long getEstimatedHours(Long sprintId, Long userId) {
		return tmsSubTaskDAO.getTotalEstimatedHoursBySprint(sprintId, userId);
	}

	private int getConsumedHours(Date sprintStartDate) {
		int days = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(sprintStartDate, new Date());
		return days * Integer.parseInt(workingHours);
	}

}

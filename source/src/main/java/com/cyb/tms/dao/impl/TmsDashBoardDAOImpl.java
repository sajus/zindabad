package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsDashBoardDAO;
import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsOrgLeavesDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.dao.TmsUsersDAO;
import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;
import com.cyb.tms.util.WorkingDaysCalculator;

@Repository
public class TmsDashBoardDAOImpl implements TmsDashBoardDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;
	
	@Autowired
	private TmsUsersDAO tmsUsersDAO;

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
	public LinkedHashMap<Object, Object> getUserDashBoardData(Long userId,Long projectId) {
		LinkedHashMap<Object, Object> dashBoard = new LinkedHashMap<Object, Object>();
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		dashBoard.put("capacity", calculateCapacity(sprint));
		Long sprintCapacity = calculateSprintCapacity(sprint, userId);
		dashBoard.put("sprintCapacity", sprintCapacity);
		int hoursConsumed = getConsumedHours(sprint.getSprintStartDate());
		dashBoard.put("hoursConsumed", hoursConsumed);
		dashBoard.put("hoursBurned", getTotalLoggedHours(userId, projectId));
		dashBoard.put("remainingHours", (sprintCapacity - hoursConsumed));
		return dashBoard;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<Object, Object>> getManagerDashBoardData(Long projectId) {
		List<LinkedHashMap<Object, Object>> dashboardList = new ArrayList<LinkedHashMap<Object, Object>>();
		for (TmsUsers tmsUsers : tmsUsersDAO.getUsersByStatus(projectId)) {
		LinkedHashMap<Object, Object> dashBoard = new LinkedHashMap<Object, Object>();
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		dashBoard.put("capacity", calculateCapacity(sprint));
		Long sprintCapacity = calculateSprintCapacity(sprint,tmsUsers.getId());
		dashBoard.put("sprintCapacity", sprintCapacity);
		int hoursConsumed = getConsumedHours(sprint.getSprintStartDate());
		dashBoard.put("hoursConsumed", hoursConsumed);
		dashBoard.put("userName",tmsUsers.getUserName());
		dashBoard.put("hoursBurned", getTotalLoggedHours(tmsUsers.getId(), projectId));
		dashBoard.put("remainingHours", (sprintCapacity - hoursConsumed));
		dashboardList.add(dashBoard);
	   }
		return dashboardList;
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

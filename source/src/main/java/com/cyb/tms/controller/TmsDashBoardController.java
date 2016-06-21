package com.cyb.tms.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.tms.service.TmsDashBoardService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.DASH_BOARD)
public class TmsDashBoardController {
	
	@Autowired
	private TmsDashBoardService tmsDashBoardService;
	
	@RequestMapping(value = URIConstants.GET_USER_DASH_BOARD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinkedHashMap<Object, Object>> listCurrentUserEffortsBySprint(@RequestParam Long userId, @RequestParam Long projectId) {
		LinkedHashMap<Object, Object> subtasks = tmsDashBoardService.getUserDashBoardData(userId, projectId);
		return new ResponseEntity<LinkedHashMap<Object, Object>>(subtasks, HttpStatus.OK);
	}

}

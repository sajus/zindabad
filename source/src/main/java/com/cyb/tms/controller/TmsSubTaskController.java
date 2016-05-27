package com.cyb.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.service.TmsSubTaskService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.SUBTASK)
public class TmsSubTaskController {
	
	@Autowired
	private TmsSubTaskService tmsSubTaskService;
	
	

	// -------------------Create a Subtask---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createSubtask(@RequestBody TmsSubtask subtask) {
		tmsSubTaskService.createSubask(subtask);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------Retrieve All Subtasks --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsSubtask>> listAllSubtasks() {
		List<TmsSubtask> stories = tmsSubTaskService.getAllSubtasks();
		return new ResponseEntity<List<TmsSubtask>>(stories, HttpStatus.OK);
	}


}
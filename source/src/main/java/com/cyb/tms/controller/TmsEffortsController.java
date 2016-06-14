package com.cyb.tms.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.tms.dto.TmsEffortsDTO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.service.TmsEffortsService;
import com.cyb.tms.util.URIConstants;


@RestController
@RequestMapping(URIConstants.EFFORTS)
public class TmsEffortsController {
	
	@Autowired
	private TmsEffortsService tmsEffortsService;
	
	

	// -------------------Create a Effort---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createSubtask(@RequestBody TmsEffortsDTO tmseffortDTO) {
		tmsEffortsService.createEffort(tmseffortDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------Retrieve All Efforts --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsEfforts>> listAllSubtasks() {
		List<TmsEfforts> subtasks = tmsEffortsService.getAllEfforts();
		return new ResponseEntity<List<TmsEfforts>>(subtasks, HttpStatus.OK);
	}
	
	@RequestMapping(value = URIConstants.USER_EFFORTS_BY_SPRINT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listCurrentUserEffortsBySprint(@RequestParam Long userId, @RequestParam Long projectId) {
		List<LinkedHashMap<String, Object>> subtasks = tmsEffortsService.getCurrentUserEffortsBySprint(userId, projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(subtasks, HttpStatus.OK);
	}

}

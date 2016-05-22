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

import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.service.TmsSprintService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.SPRINT)
public class TmsSprintController {

	public TmsSprintController() {
		System.out.println("TmsSprintController");
	}

	@Autowired
	private TmsSprintService tmsSprintService;

	// -------------------Create a Sprint---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createSprint(@RequestBody TmsSprintMst sprint) {
		tmsSprintService.createSprint(sprint);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------Retrieve All Sprints --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsSprintMst>> listAllSprints() {
		List<TmsSprintMst> sprints = tmsSprintService.getAllSprints();
		return new ResponseEntity<List<TmsSprintMst>>(sprints, HttpStatus.OK);
	}

}

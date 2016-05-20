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

import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.service.TmsProjectService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.PROJECT)
public class TmsProjectController {

	
	@Autowired
	private TmsProjectService tmsProjectService;
	
	public TmsProjectController() {
		System.out.println("TmsProjectController");
	}


	// -------------------Create a Project---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createProject(@RequestBody TmsProject project) {
		System.out.println("Creating User " + project.getName());
		tmsProjectService.createProject(project);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsProject>> listAllUsers() {
		List<TmsProject> project = tmsProjectService.getAllProjects();

		return new ResponseEntity<List<TmsProject>>(project, HttpStatus.OK);
	}
}

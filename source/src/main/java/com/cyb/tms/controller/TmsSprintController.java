package com.cyb.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.exceptions.ErrorCodes;
import com.cyb.tms.exceptions.SprintException;
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
	public ResponseEntity<Void> createSprint(@RequestBody TmsSprintDTO tmsSprintDTO) throws Exception {
		tmsSprintService.createSprint(tmsSprintDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	// ------------------Retrieve ActiveSprint by projectId --------------

	//@RequestMapping(value = URIConstants.GET_ALL_PROJECT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	//public ResponseEntity<List<TmsSprintMst>> listSprintByProjectId(@RequestParam Long projectId) throws Exception {
		//List<TmsSprintMst> sprint = tmsSprintService.getActiveSprint(projectId);
		//return new ResponseEntity<List<TmsSprintMst>>(sprint, HttpStatus.OK);
	//}
	
	// ------------------Retrieve All Sprints by projectId--------------

		@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<TmsSprintMst>> listAllSprints(@RequestParam Long projectId) throws Exception{
			List<TmsSprintMst> sprints = tmsSprintService.getAllSprints(projectId);
			return new ResponseEntity<List<TmsSprintMst>>(sprints, HttpStatus.OK);
		}
		
		//------------------- Update a Sprint --------------------------------------------------------
	     
	    @RequestMapping(value = URIConstants.EDIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Void> updateSprint(@RequestBody TmsSprintDTO tmsSprintDTO) {
	    	List<String> incompleteStories = tmsSprintService.updateSprint(tmsSprintDTO);
	    	if(incompleteStories != null & incompleteStories.size() > 0) {
	    		throw new SprintException(ErrorCodes.CLOSE, incompleteStories);
	    	}
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
			
	    }	

}

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

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.service.TmsLeaveService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.LEAVE)
public class TmsLeaveController {

	@Autowired
	TmsLeaveService tmsLeaveService;

	// -------------------Create a leave---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createLeave(@RequestBody TmsLeaveDTO tmsleaveDTO) {
		tmsLeaveService.createLeave(tmsleaveDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);



	}
	
//	// -------------------update a leave---------------
	@RequestMapping(value = URIConstants.EDIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLeave(@RequestBody TmsLeaveDTO tmsleaveDTO) {
		tmsLeaveService.updateLeave(tmsleaveDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}	
	
	@RequestMapping(value = URIConstants.DELETE, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteLeave(@PathVariable("id") String id) {
		tmsLeaveService.deleteLeave(Long.parseLong(id));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}	
	
	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET)
	public ResponseEntity<List<TmsLeaveMst>> listAllLeaves(@RequestParam Long projectId) throws Exception {
		List<TmsLeaveMst> leave = tmsLeaveService.getAllLeavesBySprint(projectId);
		if(leave.isEmpty()){
			//You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<TmsLeaveMst>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TmsLeaveMst>>(leave, HttpStatus.OK);
	}

	// ------------------Retrieve Leave by Sprint --------------
	@RequestMapping(value = URIConstants.GET_ALL_BY_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsLeaveMst>> listLeaveBySprint(@RequestParam Long userId, Long projectId) throws Exception {
		List<TmsLeaveMst> leave = tmsLeaveService.getCurrentUserLeavesBySprint(userId, projectId);
		return new ResponseEntity<List<TmsLeaveMst>>(leave, HttpStatus.OK);
	}
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.service.TmsLeaveService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.LEAVE)
public class TmsLeaveController {

	@Autowired
	TmsLeaveService tmsLeaveService;
	
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> createLeave(@RequestBody TmsLeaveMst leave) {
		
		System.out.println("Creating User " + leave.getLeavetype());
		 
		/*if (leaveService.isUserExist(leaves)) {
            System.out.println("Leave for the date  " + leaves.getDate() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
		tmsLeaveService.createLeave(leave);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	 @RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET)
	    public ResponseEntity<List<TmsLeaveMst>> listAllLeaves() {
	        List<TmsLeaveMst> leave = tmsLeaveService.getAllLeaves();
	        if(leave.isEmpty()){
	            return new ResponseEntity<List<TmsLeaveMst>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<TmsLeaveMst>>(leave, HttpStatus.OK);
	    }
	 
	// ------------------Retrieve Leave by Sprint --------------

		@RequestMapping(value = URIConstants.GET_ALL_BY_ID, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<TmsLeaveMst>> listLeaveBySprint(@RequestParam Long projectId) throws Exception {
			List<TmsLeaveMst> leave = tmsLeaveService.getLeaveBySprint(projectId);
			return new ResponseEntity<List<TmsLeaveMst>>(leave, HttpStatus.OK);
		}
}

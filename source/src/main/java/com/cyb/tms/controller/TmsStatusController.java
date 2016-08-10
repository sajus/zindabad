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

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.service.TmsModuleService;
import com.cyb.tms.service.TmsProjectService;
import com.cyb.tms.service.TmsStatusService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.STATUS)
public class TmsStatusController {

	public TmsStatusController() {
		System.out.println("TmsStatusController");
	}
	
	@Autowired TmsStatusService tmsStatusService;
	
	//------------------- Get Status List --------------------------------------------------------
 	
	 	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<TmsStatusMst>> listAllStatus() {
			List<TmsStatusMst> status = tmsStatusService.getAllStatus();
			return new ResponseEntity<List<TmsStatusMst>>(status, HttpStatus.OK);
		}

}

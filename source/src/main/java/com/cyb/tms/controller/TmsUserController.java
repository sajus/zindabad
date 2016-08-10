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

import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.service.TmsUserService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.USER)
public class TmsUserController {

	@Autowired
	private TmsUserService tmsUserService;

	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody TmsUsersDTO tmsUserDTO) {

		System.out.println("Creating User " + tmsUserDTO.getUserName());

		if (tmsUserService.isUserExist(tmsUserDTO.getUserName())) {
			System.out.println("A User with name " + tmsUserDTO.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		tmsUserService.createUser(tmsUserDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET)
	public ResponseEntity<List<TmsUsers>> listAllUsers() {
		List<TmsUsers> users = tmsUserService.getAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<TmsUsers>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<TmsUsers>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.GET_ALL_BY_PROJECT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsUsers>> listAllUsersByProject(@RequestParam Long projectId) throws Exception {
		List<TmsUsers> activeuser = tmsUserService.getUsersByStatus(projectId);
		return new ResponseEntity<List<TmsUsers>>(activeuser, HttpStatus.OK);
	}
	
	@RequestMapping(value = URIConstants.GET_USER_LIST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsUsers>> listAllUsers(@RequestParam Long projectId) throws Exception {
		List<TmsUsers> activeuser = tmsUserService.getUsersByStatusDashboard(projectId);
		return new ResponseEntity<List<TmsUsers>>(activeuser, HttpStatus.OK);
	}

//------------------- Edit User --------------------------------------------------------
    
    @RequestMapping(value = URIConstants.UPDATE_STATUS, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editCodeReview(@RequestBody TmsUsersDTO tmsUserDTO) {
    	tmsUserService.updateUser(tmsUserDTO);
    	HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
    
    @RequestMapping(value = URIConstants.UPDATE_PASSWORD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassword(@RequestBody TmsUsersDTO tmsUserDTO) {
    	tmsUserService.updatePassword(tmsUserDTO);
    	HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
    

}

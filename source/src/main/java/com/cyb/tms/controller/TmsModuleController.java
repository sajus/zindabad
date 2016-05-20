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

import com.cyb.tms.dto.TmsModuleDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.service.TmsModuleService;
import com.cyb.tms.service.TmsProjectService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.MODULE)
public class TmsModuleController {

	public TmsModuleController() {
		System.out.println("TmsModuleController");
	}

	@Autowired
	private TmsModuleService tmsModuleService;

	@Autowired
	private TmsProjectService tmsProjectService;

	// -------------------Create Module---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createModule(@RequestBody TmsModuleDTO moduleDto) {
		TmsModule tmsModule = new TmsModule();
		setDtoToDo(tmsModule, moduleDto);
		tmsModuleService.createModule(tmsModule);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsModuleDTO>> listAllUsers() {
		List<TmsModuleDTO> modules = tmsModuleService.getAllModules();
		return new ResponseEntity<List<TmsModuleDTO>>(modules, HttpStatus.OK);
	}
	
	private void setDtoToDo(TmsModule tmsModule, TmsModuleDTO moduleDto) {
		tmsModule.setModuleName(moduleDto.getModuleName());
		tmsModule.setModuleDescription(moduleDto.getModuleDescription());
		tmsModule.setTmsProject(tmsProjectService.getProject(moduleDto.getProjectId()));
	}

}

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
import com.cyb.tms.dto.TmsTaskTypeDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsTaskType;
import com.cyb.tms.service.TmsProjectService;
import com.cyb.tms.service.TmsTaskTypeService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.TASKTYPE)
public class TmsTaskTypeController {
	
	public TmsTaskTypeController() {
		System.out.println("TmsTaskTypeController");
	}

	@Autowired
	private TmsTaskTypeService tmsTaskTypeService;

	@Autowired
	private TmsProjectService tmsProjectService;


	// -------------------Create TaskType---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createTaskType(@RequestBody TmsTaskTypeDTO taskTypeDto) {
		TmsTaskType tmsTaskType = new TmsTaskType();
		setDtoToDo(tmsTaskType, taskTypeDto);
		tmsTaskTypeService.createTaskType(tmsTaskType);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsTaskTypeDTO>> listAllTaskTypes() {
		List<TmsTaskTypeDTO> taskTypes = tmsTaskTypeService.getAllTaskTypes();
		return new ResponseEntity<List<TmsTaskTypeDTO>>(taskTypes, HttpStatus.OK);
	}
	
	private void setDtoToDo(TmsTaskType tmsTaskType, TmsTaskTypeDTO taskTypeDto) {
		tmsTaskType.setTaskTypeName(taskTypeDto.getTaskTypeName());
		tmsTaskType.setTaskTypeDescription(taskTypeDto.getTaskTypeDescription());
		tmsTaskType.setTmsProject(tmsProjectService.getProject(taskTypeDto.getProjectId()));
	}
	
	//------------------- Edit a TaskType --------------------------------------------------------
    
    @RequestMapping(value = URIConstants.EDIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editTaskType(@RequestBody TmsTaskTypeDTO taskTypeDto) {
    	tmsTaskTypeService.editTaskType(taskTypeDto);
    	HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	} 

}

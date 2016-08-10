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

import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.service.TmsSubTaskService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.SUBTASK)
public class TmsSubTaskController {

	@Autowired
	private TmsSubTaskService tmsSubTaskService;



	// -------------------Create a Subtask---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createSubtask(@RequestBody SubtaskDTO subtaskDTO) {
		tmsSubTaskService.createSubtask(subtaskDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Add Story to current sprint---------------
	@RequestMapping(value = URIConstants.ASSIGN_TO_SPRINT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addToCurrentSprint(@RequestBody List<SubtaskDTO> subtaskDTOs, @RequestParam Long projectId, @RequestParam Long assignToId, @RequestParam Long modifiedById) {
		tmsSubTaskService.addToCurrentSprint(subtaskDTOs, projectId, assignToId, modifiedById);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------Retrieve All Subtasks --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsSubtask>> listAllSubtasks() {
		List<TmsSubtask> stories = tmsSubTaskService.getAllSubtasks();
		return new ResponseEntity<List<TmsSubtask>>(stories, HttpStatus.OK);
	}


	// ------------------Retrieve All Subtasks by Sprint --------------

	@RequestMapping(value = URIConstants.SUBTASKS_BY_SPRINT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listSubtasksBySprint(@RequestParam Long projectId) throws Exception {
		List<LinkedHashMap<String, Object>> subtasks = tmsSubTaskService.getSubtasksBySprint(projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(subtasks, HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.BACKLOG, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listBackLogStories(@RequestParam Long projectId) throws Exception {
		List<LinkedHashMap<String, Object>> stories = tmsSubTaskService.getBackLogSubtasks(projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(stories, HttpStatus.OK);
	}


	@RequestMapping(value = URIConstants.USER_SUBTASKS_BY_SPRINT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> getCurrentUserSubTasksBySprintBy(@RequestParam Long userId, Long projectId) throws Exception {
		List<LinkedHashMap<String, Object>> stories = tmsSubTaskService.getCurrentUserSubTasksBySprintBy(userId, projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(stories, HttpStatus.OK);
	}

	// -------------------Update a Subtask---------------
	@RequestMapping(value = URIConstants.UPDATE_STATUS, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateSubtaskStatus(@RequestBody SubtaskDTO subtaskDTO) {
		tmsSubTaskService.updateSubtaskStatus(subtaskDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	//------------------- Edit a Subtask --------------------------------------------------------

	@RequestMapping(value = URIConstants.EDIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editSubtask(@RequestBody SubtaskDTO subtaskDTO) {
		tmsSubTaskService.editSubtask(subtaskDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	} 
	
	@RequestMapping(value = URIConstants.SUBTASKS_BY_STORY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listSubtasks(@RequestParam Long storyId) {
		List<LinkedHashMap<String, Object>> subtasks = tmsSubTaskService.fetchSubtasksByStoryId(storyId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(subtasks, HttpStatus.OK);
	}

}

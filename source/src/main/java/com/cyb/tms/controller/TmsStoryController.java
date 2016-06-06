package com.cyb.tms.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.service.TmsModuleService;
import com.cyb.tms.service.TmsStoryService;
import com.cyb.tms.service.TmsUserService;
import com.cyb.tms.util.URIConstants;

@RestController
@RequestMapping(URIConstants.STORY)
public class TmsStoryController {
	
	@Autowired
	private TmsStoryService tmsStoryService;
	
	@Autowired
	private TmsModuleService tmsModuleService;
	
	@Autowired
	private TmsUserService tmsUserService;

	

	// -------------------Create a Story---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createStory(@RequestBody StoryDTO storyDTO) {
		tmsStoryService.createStory(storyDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	// -------------------Add Story to current sprint---------------
	/*	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> addToCurrentSprint(@RequestBody StoryDTO storyDTO) {
			// TODO
			tmsStoryService.addToCurrentSprint(storyDTO);
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}*/
		

	// ------------------Retrieve All Stories --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsStoryMst>> listAllStories() {
		List<TmsStoryMst> stories = tmsStoryService.getAllStories();
		return new ResponseEntity<List<TmsStoryMst>>(stories, HttpStatus.OK);
	}
	
	// ------------------Retrieve Stories by Sprint --------------

	@RequestMapping(value = URIConstants.STORIES_BY_SPRINT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listStoriesBySprint(@RequestParam Long userId, Long projectId) throws Exception {
		List<LinkedHashMap<String, Object>> stories = tmsStoryService.getStoriesBySprint(projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(stories, HttpStatus.OK);
	}

	
}

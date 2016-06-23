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

import com.cyb.tms.dto.CodeReviewDTO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.service.TmsCodeReviewService;
import com.cyb.tms.util.URIConstants;


@RestController
@RequestMapping(URIConstants.CODE_REVIEW)
public class TmsCodeReviewController {
	
   @Autowired
   private TmsCodeReviewService tmsCodeReviewService;

	// -------------------Create a Review---------------
	@RequestMapping(value = URIConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCodeReview(@RequestBody CodeReviewDTO codeReviewDTO) {
		tmsCodeReviewService.createReview(codeReviewDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------Retrieve All Review --------------

	@RequestMapping(value = URIConstants.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TmsCodeReview>> listAllReviews() {
		List<TmsCodeReview> reviewlist = tmsCodeReviewService.getAllReviews();
		return new ResponseEntity<List<TmsCodeReview>>(reviewlist, HttpStatus.OK);
	}
	
	@RequestMapping(value = URIConstants.CODE_REVIEW_BY_SPRINT , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinkedHashMap<String, Object>>> listCurrentUserStoriesBySprint(@RequestParam Long userId, Long projectId) throws Exception {
		List<LinkedHashMap<String, Object>> codereviewlist = tmsCodeReviewService.getCodeReviewForCureentUserBySprint(userId, projectId);
		return new ResponseEntity<List<LinkedHashMap<String, Object>>>(codereviewlist, HttpStatus.OK);
	}
	
	//------------------- Edit a Review --------------------------------------------------------
    
    @RequestMapping(value = URIConstants.EDIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editCodeReview(@RequestBody CodeReviewDTO codeReviewDTO) {
    	tmsCodeReviewService.editCodeReview(codeReviewDTO);
    	HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	} 
}

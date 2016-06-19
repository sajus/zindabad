'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("sprintService", function($http, appConstants) {


		var sprintService = {};

		sprintService.getSprint = function () {
        	return $http({
			    url: appConstants.endPointBase+"api/sprint/list", 
			    method: "POST",
			    params: {
			  		projectId: appConstants.user.projectId
			  	}
			 });
        
    	};


    	sprintService.updateSprint = function (sprint) {
    		sprint.projectId = appConstants.user.projectId;
	        return $http({
				    url: appConstants.endPointBase+"api/sprint/edit", 
				    method: "POST",
				    data: sprint
				 });

 		};
    	

    	sprintService.addSprint = function (newSprint) {
	       newSprint.projectId = appConstants.user.projectId;
	        return $http({
				    url: appConstants.endPointBase+"api/sprint/create", 
				    method: "POST",
				    data: newSprint
				 });

 		};
 return sprintService;

  

	});
	
	
});
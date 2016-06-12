'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("subtaskService", function($http, appConstants) {

var subtaskService = {};

		subtaskService.getSubtask = function () {

			var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/subtask/user/sprint", 
				 params: {
			  		projectId: appConstants.user.projectId, 
			    	userId: appConstants.user.id
			  	}
			}

			return $http(req);
   	    };


   	    subtaskService.getUnassignedSubtask = function () {
   	    	var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/subtask/backlog", 
				 params: {
			  		projectId: appConstants.user.projectId
			  	}
			}
			return $http(req);

   	    };
   	    	
   		 subtaskService.update = function () {
        	return $http.get(appConstants.endPointBase+"api/subtask/list");
    	};

	 return subtaskService;

	});
	
	
	
});

'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("subtaskService", function($http, appConstants) {

var subtaskService = {};

		subtaskService.getSubtask = function () {

			var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/subtask/sprint", 
				 params: {
			  		projectId: appConstants.user.projectId, 
			    	userId: appConstants.user.id
			  	}
			}

			return $http(req);
			// var url = appConstants.endPointBase+"api/story/sprint", 
			 // var parameters = {
    //             	projectId: appConstants.user.projectId, 
				// 	userId: appConstants.user.userId
    //         };
    //         var config = {
    //             params: parameters
    //         };
			 // var config : {
			 // 	params: {
			 // 		projectId: appConstants.user.projectId, 
				// 	userId: appConstants.user.userId
			 // 	}
			 // }
			// return $http.get(url, {
			//  		projectId: appConstants.user.projectId, 
			// 	 	userId: appConstants.user.userId
			//   	});
							
   	    };

   		 subtaskService.update = function () {
        	return $http.get(appConstants.endPointBase+"api/subtask/list");
    	};

	 return subtaskService;

	});
	
	
	
});

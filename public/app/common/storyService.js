'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("storyService", function($http, appConstants) {

		var storyService = {};

		storyService.getStories = function () {

			var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/story/sprint", 
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

   		 storyService.update = function () {
        	return $http.get(appConstants.endPointBase+"api/story/list");
    	};

	 return storyService;

	});
	
	
});

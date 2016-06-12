'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("storyService", function($http, appConstants) {

		var self = this;

		self.getStories = function () {

			var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/story/sprint", 
				 params: {
			  		projectId: appConstants.user.projectId, 
			    	userId: appConstants.user.id
			  	}
			}

			return $http(req);
   	    };

   	    self.getBackLogStories = function () {

   	    	var req = {
				 method: 'GET',
				 url: appConstants.endPointBase+"api/story/backlog", 
				 params: {
			  		projectId: appConstants.user.projectId
			  	}
			}

			return $http(req);
   	    };


   		 self.update = function () {
        	return $http.get(appConstants.endPointBase+"api/story/list");
    	};

	});
	
	
});

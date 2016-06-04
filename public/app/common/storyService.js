'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("storyService", function($http, appConstants) {


		var storyService = {};

		storyService.getStory = function () {
        return $http.get(appConstants.endPointBase+"api/story/list");
    };

    storyService.update = function () {
        return $http.get(appConstants.endPointBase+"api/story/list");
    };

 return storyService;


	});
	
	
});

'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("sprintService", function($http, appConstants) {


		var sprintService = {};

		sprintService.getSprint = function () {
        return $http.get(appConstants.endPointBase+"api/sprint/list");
        
    };

    // 	sprintService.updateSprintData= function (id) {

    //     return $http.put(appConstants.endPointBase+"api/sprint/edit/{id}",sprint);
    // };


 return sprintService;

  

	});
	
	
});

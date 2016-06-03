'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("userService", function($http, appConstants) {


		var userService = {};

		userService.getUser = function () {
        return $http.get(appConstants.endPointBase+"api/user/list");
        
    };

    // 	sprintService.updateSprintData= function (id) {

    //     return $http.put(appConstants.endPointBase+"api/sprint/edit/{id}",sprint);
    // };


 return userService;

  

	});
	
	
});

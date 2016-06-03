'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("leaveService", function($http, appConstants) {

		var leaveService = {};

		leaveService.getLeave = function () {
        return $http.post(appConstants.endPointBase+"api/leave/list/project?projectId="+appConstants.user.projectId);
        
    };

    return leaveService;

	});
	
	
});

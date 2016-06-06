'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("leaveService", function($http, appConstants) {

		var leaveService = {};

		leaveService.getLeaves = function () {
			return $http({
			    url: appConstants.endPointBase+"api/leave/list/project", 
			    method: "POST",
			    params: {projectId: appConstants.user.projectId}
			 });
        
    	};

    return leaveService;

	});
});

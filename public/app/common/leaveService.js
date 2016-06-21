'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("leaveService", function($http, appConstants) {

		var leaveService = {};

		leaveService.getLeaves = function () {
			return $http({
			    url: appConstants.endPointBase+"api/leave/list/user",
			    method: "GET",
			    params: {
			        projectId: appConstants.user.projectId,
			        userId: appConstants.user.id
                }
			 });
        
    	};

    	leaveService.updateLeave = function (leave) {
    	    leave.projectId = appConstants.user.projectId;
    	    leave.userId = appConstants.user.id;
            return $http({
                url: appConstants.endPointBase+"api/leave/edit",
                method: "POST",
                data : leave
             });

        };

        leaveService.addLeave = function (leave) {
            leave.projectId = appConstants.user.projectId;
            leave.userId = appConstants.user.id;
            return $http({
                url: appConstants.endPointBase+"api/leave/create",
                method: "POST",
                data : leave
            });
        };

         leaveService.deleteLeave = function (leaveId) {
            return $http({
                url: appConstants.endPointBase+"api/leave/delete/:id",
                params: {id: leaveId},
                method: "GET"
            });
         };

    return leaveService;

	});
});

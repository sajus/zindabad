'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("dashboardService", function($http, appConstants) {

		var self = this;

		self.userDashBoardDetails = function () {
      return $http({
        url: appConstants.endPointBase+"api/dashboard/user",
        method: "GET",
        params: {
          projectId: appConstants.user.projectId,
          userId: appConstants.user.id
        }
      });
    };
	});
});

'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("statusService", function($http, appConstants) {

    var statusService = {};

      statusService.getStatus = function () {
        return $http.get(appConstants.endPointBase+"api/status/list");
      }

  return statusService;

  });
	
});
'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("manageService", function($http, appConstants) {

    var manageService = {};

		manageService.getUser = function () {
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/user/list/project",
        params: {
          projectId: appConstants.user.projectId
        }
      }
      return $http(req);
    };

    manageService.getModule = function () {
      return $http.get(appConstants.endPointBase+"api/module/list");
    };

    manageService.addUser = function (user) {
      user.projectId = appConstants.user.projectId;
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/user/create",
        data: user
      }
      return $http(req);
    };

    manageService.saveUserStatus = function (user) {
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/user/update",
        data: user
      }
      return $http(req);
    };

    manageService.addModule = function (module) {
      module.projectId = appConstants.user.projectId;
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/module/create",
        data: module
      }
      return $http(req);
    };

    manageService.editModule = function (module) {
      return $http({
        url: appConstants.endPointBase+"api/module/edit",
        method: "POST",
        data: module
     });
    };

  return manageService;
  });
	
});

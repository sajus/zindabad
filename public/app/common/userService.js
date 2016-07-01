'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("userService", function($http, appConstants) {

        var userService = {};

    		userService.getUser = function () {
          return $http.get(appConstants.endPointBase+"api/user/list");

        };

        userService.addUser = function (user) {
            var req = {
                method: 'POST',
                url: appConstants.endPointBase+"api/user/create",
                data: user
            }
            return $http(req);
        };

        userService.saveUserStatus = function (user) {
            var req = {
                method: 'POST',
                url: appConstants.endPointBase+"api/user/update",
                data: user
            }
            return $http(req);
        };


    return userService;


    });
	
	
});

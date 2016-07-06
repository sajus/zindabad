'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("passwordService", function($http, appConstants) {

    var passwordService = {};

    passwordService.updatePassword = function (user) {
      user.id = appConstants.user.id;
          return $http({
            url: appConstants.endPointBase+"api/user/password", 
            method: "POST",
            data: user
         });

    };

  return passwordService;

  });
	
});
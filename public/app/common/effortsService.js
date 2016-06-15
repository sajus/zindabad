'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("effortsService", function($http, appConstants) {

		var effortsService = {};

		effortsService.getEfforts = function () {
    	return $http({
		    url: appConstants.endPointBase+"api/efforts/user/sprint", 
		    method: "GET",
		    params: {
		   		projectId: appConstants.user.projectId,
		  		userId: appConstants.user.id
		  	}
		  });
  	};

  	effortsService.saveEfforts = function (effort) {
  		effort.projectId = appConstants.user.projectId;
        return $http({
					url: appConstants.endPointBase+"api/efforts/create", 
			    method: "POST",
			    data: effort
			 });
 		};

	 return effortsService;
	});
});

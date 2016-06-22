'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.factory', ['$http', 'appConstants']).factory("codereviewService", function($http, appConstants) {

		var codereviewService = {};

		codereviewService.getReviews = function () {
    	return $http({
		    url: appConstants.endPointBase+"api/review/user/sprint", 
		    method: "GET",
		     params: {
          projectId: appConstants.user.projectId,
          userId: appConstants.user.id
        }
		  });
  	};

  	codereviewService.editReview = function (review) {
	        return $http({
				    url: appConstants.endPointBase+"api/review/edit", 
				    method: "POST",
				    data: review
				 });

 		};

	 return codereviewService;
	});
});

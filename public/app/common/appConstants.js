'use strict';
define([
	'angular',
], function(angular) {

	return angular.module('myApp.factory', ['localStorageService']).factory('appConstants', function(localStorageService) {
		return {

			endPointBase: 'http://localhost:8080/TaskManagement/',
			
			config :{
				headers: {
					'Context-Type' : 'application/json'
				}
			},

			getLocalStorage : function() {
				return "sime";
			}

		}
	
	});
	
	

});    
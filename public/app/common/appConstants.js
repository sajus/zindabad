'use strict';
define([
	'angular',
], function(angular) {

	return angular.module('myApp.factory', ['localStorageService']).factory('appConstants', function(localStorageService) {
		return {

			endPointBase: 'http://localhost:9090/TaskManagement/',

			config :{
				headers: {
					'Context-Type' : 'application/json'
				}
			},

			user : localStorageService.get('currentUser'),

			isAuthenticated: function(){
				return (localStorageService.get('currentUser') !== null) ? true : false;
			},	

			getStorageType : function() {
				return localStorageService.getStorageType();
			},

			getItem: function(key) {
			   return localStorageService.get(key);
			},

			setItem: function(key, val) {
				return localStorageService.set(key, val);
			},

			clearAll: function() {
				return localStorageService.clearAll();
			}

		}
	
	});
	
	

});    
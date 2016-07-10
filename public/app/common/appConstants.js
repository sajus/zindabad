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
			},

			getStatusList: function() {
        return [
          {value: "BACKLOG", name: "Backlog"},
          {value: "TODO", name: "ToDo"},
          {value: "DEVELOPMENT", name: "Development"},
          {value: "PULL REQUEST", name: "Pull Request"},
          {value: "INTERNAL REVIEW", name: "Internal Review"},
          {value: "QUALITY", name: "Quality"},
          {value: "REOPEN", name: "Reopen"},
          {value: "CODE MERGED", name: "Code Merged"},
          {value: "CLOSED", name: "Closed"}
        ];
		
			},

			getStatus: function() {
	      return [
	        {value: "ACTIVE", name: "Active"},
	        {value: "INACTIVE", name: "Inactive"}
	      ];
		
			},
			
			getRole: function(){
				return [
					{value: "LEAD", name: "Lead"},
					{value: "DEVELOPER", name:"Developer"}
				];
			}

		}
		
	});
	
});    

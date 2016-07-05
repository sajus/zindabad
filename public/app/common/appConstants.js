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
          {value: "Backlog", name: "Backlog"},
          {value: "ToDo", name: "ToDo"},
          {value: "Development", name: "Development"},
          {value: "PullRequest", name: "PullRequest"},
          {value: "Internal_Review", name: "Internal_Review"},
          {value: "Quality", name: "Quality"},
          {value: "Reopen", name: "Reopen"},
          {value: "Code_Merged", name: "Code_Merged"},
          {value: "Closed", name: "Closed"}
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
					{value: "Lead", name: "Lead"},
					{value: "Developer", name:"Developer"}
				];
			}

		}
		
	});
	
});    

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
          {value: "BACKLOG", name: "BACKLOG"},
          {value: "TODO", name: "TODO"},
          {value: "DEVELOPMENT", name: "DEVELOPMENT"},
          {value: "PULL REQUEST", name: "PULL REQUEST"},
          {value: "INTERNAL REVIEW", name: "INTERNAL REVIEW"},
          {value: "QUALITY", name: "QUALITY"},
          {value: "REOPEN", name: "REOPEN"},
          {value: "CODE MERGED", name: "CODE MERGED"},
          {value: "CLOSED", name: "CLOSED"},
          {value: "NEXT SPRINT", name: "NEXT SPRINT"},
          {value: "BLOCKED", name: "BLOCKED"}
        ];
			},

			getList: function() {
        return [
          {value: "DOMO STANDARD", name: "DOMO STANDARD"},
          {value: "ANGULARRJS EXPERIENCE", name: "ANGULARRJS EXPERIENCE"},
          {value: "CLIENT CODE", name: "CLIENT CODE"},
          {value: "ENHANCEMENT", name: "ENHANCEMENT"},
          {value: "CODE OPTIMIZATION", name: "CODE OPTIMIZATION"},
          {value: "REQUIREMENT CONFLICT", name: "REQUIREMENT CONFLICT"},
          {value: "REVIEWER CONFLICT", name: "REVIEWER CONFLICT"},
          {value: "APPRECIATION", name: "APPRECIATION"},
          {value: "NA", name: "NA"}
        ];
			},

			getReviewerType: function() {
	      return [
	        {value: "PEER", name: "PEER"},
	        {value: "CLIENT", name: "CLIENT"},
	        {value: "QA", name: "QA"}
	      ];
			},

			getScope: function() {
	      return [
	        {value: "PLANNED", name: "PLANNED"},
	        {value: "UNPLANNED", name: "UNPLANNED"}
	      ];
			},

			getSubtaskType: function() {
	      return [
	        {value: "BUG", name: "BUG"},
	        {value: "SUBTASK", name: "SUBTASK"}
	      ];
			},

			getSprintStatus: function() {
	      return [
	        {value: "OPEN", name: "OPEN"},
	        {value: "CLOSED", name: "CLOSED"}
	      ];
			},

			getStatus: function() {
	      return [
	        {value: "ACTIVE", name: "ACTIVE"},
	        {value: "INACTIVE", name: "INACTIVE"},
	        {value: "DELETE", name: "DELETE"}
	      ];
			},
			
			getRole: function(){
				return [
					{value: "LEAD", name: "LEAD"},
					{value: "DEVELOPER", name:"DEVELOPER"}
				];
			}
		}
		
	});
});    

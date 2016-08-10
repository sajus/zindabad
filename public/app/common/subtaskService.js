'use strict';
define([
  'angular',
], function(angular) {
  return angular.module('myApp.factory', ['$http', 'appConstants']).factory("subtaskService", function($http, appConstants) {

    var subtaskService = {};

    subtaskService.addSubtask = function (subtask) {
      subtask.projectId = appConstants.user.projectId;
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/subtask/create",
        data: subtask
      }
      return $http(req);
    };

    subtaskService.getStories = function () {
      var req = {
        method: 'GET',
        url: appConstants.endPointBase+"api/story/user/allusers",
        params: {
          projectId: appConstants.user.projectId,
          userId: appConstants.user.id
        }
      }
      return $http(req);
    };

    subtaskService.getSubtasks = function () {
      var req = {
        method: 'GET',
        url: appConstants.endPointBase+"api/subtask/user/sprint",
        params: {
          projectId: appConstants.user.projectId,
          userId: appConstants.user.id
        }
      }
      return $http(req);
    };

    subtaskService.getUnassignedSubtasks = function () {
      var req = {
        method: 'GET',
        url: appConstants.endPointBase+"api/subtask/backlog",
        params: {
          projectId: appConstants.user.projectId
        }
      }
      return $http(req);
    };

    subtaskService.getStatus = function () {
     return $http.get(appConstants.endPointBase+"api/status/list");
    }
          
    subtaskService.update = function () {
      return $http.get(appConstants.endPointBase+"api/subtask/list");
    };

    subtaskService.saveSubtaskStatus = function (subtask) {
     
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/subtask/update",
        data: subtask
      }
      return $http(req);
    };

    subtaskService.assignToSprint = function(selectedSubtaskList, assignToId){
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/subtask/assign/sprint",
        data: selectedSubtaskList,
        params: {
          projectId: appConstants.user.projectId,
          assignToId: assignToId,
          modifiedById: appConstants.user.id
        }
      }
      return $http(req);
    };

    subtaskService.addReview = function (review) {
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/review/create",
        data: review
      }
      return $http(req);
    };

    subtaskService.editSubtask = function(subtask){
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/subtask/edit",
        data: subtask
      }
      return $http(req);
    };

  return subtaskService;

  });
  
});

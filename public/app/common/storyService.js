'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("storyService", function($http, appConstants) {

    var self = this;

    self.addStory = function(story) {
      story.projectId = appConstants.user.projectId;
      story.userId = appConstants.user.id;
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/story/create",
        data: story
      }
      return $http(req);
    }

    self.getStories = function () {
      var req = {
        method: 'GET',
        url: appConstants.endPointBase+"api/story/user/sprint",
        params: {
          projectId: appConstants.user.projectId,
          userId: appConstants.user.id
        }
      }
      return $http(req);
    };

    self.getAllCurrentUserStoriesBySprint = function () {
      var req = {
        method: 'GET',
        url: appConstants.endPointBase+"api/story/user/allusers",
        params: {
          projectId: appConstants.user.projectId,
        }
      }
      return $http(req);
    }; 

    self.getBackLogStories = function () {
      var req = {
         method: 'GET',
         url: appConstants.endPointBase+"api/story/backlog",
         params: {
            projectId: appConstants.user.projectId
         }
      }
      return $http(req);
    };


    self.update = function () {
      return $http.get(appConstants.endPointBase+"api/story/list");
    };

    self.assignToSprint = function(selectedStoryList, assignToId){
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/story/assign/sprint",
        data: selectedStoryList,
        params: {
          projectId: appConstants.user.projectId,
          assignToId: assignToId,
          modifiedById: appConstants.user.id
        }
      }
      return $http(req);
    };

    self.saveStoryStatus = function (story) {
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/story/update",
        data: story
      }
      return $http(req);
    };

    self.editStory = function (story) {
      var req = {
        method: 'POST',
        url: appConstants.endPointBase+"api/story/edit",
        data: story
      }
      return $http(req);
    };

  });
});

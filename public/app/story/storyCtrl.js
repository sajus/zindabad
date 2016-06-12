'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'storyService', 'appConstants', '$location', function($scope, $rootScope, storyService, appConstants, $location) {
	
	$scope.changeTab = function (currentTab) {
		currentTab === 'BACKLOG' ? getBackLogStories() : getStories();
		$scope.selectedTab = currentTab;
	};

  $scope.closeModal = closeModal;
  $scope.story = {};
  $scope.errorMessage = undefined;
	$scope.changeTab('CURRENT');

	$scope.showModal = function(id, story) {
    $scope.story = angular.copy(story) || {};
    var element = angular.element(id);
    element.modal('show');
  }

  $scope.addStory = function(story) {
    storyService.addStory(story)
      .success(function () {
        getBackLogStories();
        closeModal('#add');
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
  }

  function closeModal(id) {
    clearErrorMessages();
    var element = angular.element(id);
    element.modal('hide');
  }

	function getStories() {
    storyService.getStories()
      .success(function (dataStories) {
       $scope.stories = dataStories;
      })
      .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
      });
  }
    
    function getBackLogStories() {
      storyService.getBackLogStories()
        .success(function (backlogtories) {
         $scope.backlogtories = backlogtories;
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
    }

	$scope.$apply();
		
	}];
});
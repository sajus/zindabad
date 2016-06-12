'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'storyService', 'appConstants', '$location', function($scope, $rootScope, storyService, appConstants, $location) {
	
	$scope.changeTab = function (currentTab) {
		currentTab === 'BACKLOG' ? getBackLogStories() : getStories();
		$scope.selectedTab = currentTab;
	};

	$scope.changeTab('CURRENT');

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

	$scope.$apply();
		
	}];
});
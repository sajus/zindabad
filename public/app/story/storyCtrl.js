'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'storyService', 'appConstants', '$location', function($scope, $rootScope, storyService, appConstants, $location) {
	
	function getStories() {
        storyService.getStories()
            .success(function (dataStories) {
             $scope.stories = dataStories; 
            })
            .error(function (error) {
                $scope.status = 'Unable to process your request: ' + error.message;
            });
    }
    
    getStories();

	$scope.$apply();
		
	}];
});
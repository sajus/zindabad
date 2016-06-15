'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'storyService', 'userService', 'appConstants', '$location', function($scope, $rootScope, storyService, userService, appConstants, $location) {
	
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

    function getUser() {
      userService.getUser()
        .success(function (dataUser) {
          $scope.users = dataUser;
          
                
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }
  
    getUser();


  $scope.user =  appConstants.getItem('currentUser');

  $scope.model = {
   selectedStoryList : []
  }
  $scope.isSelectAll = function(){
     $scope.model.selectedStoryList = [];

     if($scope.master){
        $scope.master = true;
        for(var i=0;i<$scope.backlogtories.length;i++){
          $scope.model.selectedStoryList.push($scope.backlogtories[i].name);  
        }
      }
      else{
        $scope.master = false;
      }

      angular.forEach($scope.backlogtories, function (item) {
        item.selected = $scope.master;
      });

    }

  $scope.isLabelChecked = function(){
    var _name = this.story.name;
    
    if(this.story.selected){
    $scope.model.selectedStoryList.push(_name);
    if($scope.model.selectedStoryList.length == $scope.backlogtories.length )
    {
     $scope.master = true;
    }
    }else{
    $scope.master = false;
    var index = $scope.model.selectedStoryList.indexOf(_name);
    $scope.model.selectedStoryList.splice(index, 1);
  }
}
  
	$scope.$apply();
		
	}];
});
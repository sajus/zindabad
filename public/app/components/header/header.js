'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',['appConstants', 'navMenus', '$location', '$routeProvider', '$compile', '$http']).directive('appHeader', function (appConstants, navMenus, $location, $compile, $http) {
	return {
		restrict: 'E',
		templateUrl: 'components/header/header.html',
		$scope: { },
		replace: false,
		controller: function($scope, $attrs, $element) {

			$scope.changeTab = function (menu, $index) {
				$location.path('/'+menu.key);
				$scope.selectedTab = $index;
			}

			$scope.$on('$destroy', function(){
        $element.remove();
        $element = null;
        $scope.$destroy();
        $scope = null;
      })

			//var init = function () {
				
				
      $scope.$watch('isAuthenticated', function () {
        $scope.user = appConstants.getItem('currentUser');
        $scope.isAuthenticated = appConstants.isAuthenticated();
        $scope.menus = $scope.isAuthenticated ? navMenus.getMenus(): [];
      })

      function getFilteredMenus() {
        return _.filter(navMenus.getMenus(), function(menu){
          return menu.accessTo.indexOf($scope.user.userRole) > -1;
        });
      }

      function getCurrentTab(path) {
        var filtered = getFilteredMenus();
        var result =  _.indexOf(_.map(filtered, 'key'));
        return _.indexOf(_.pluck(filtered, 'key'), path);
      }


			//Todo
// 			app.factory('DoNotReloadCurrentTemplate', ['$route', function($route) {
//   return function(scope) {
//     var lastRoute = $route.current;
//     scope.$on('$locationChangeSuccess', function() {
//       if (lastRoute.$$route.templateUrl === $route.current.$$route.templateUrl) {
//         console.log('DoNotReloadCurrentTemplate not reloading template: ' + $route.current.$$route.templateUrl);
//         $route.current = lastRoute;
//       }
//     });
//   };
// }]);
//https://github.com/angular/angular.js/issues/1699
		
			$scope.logout = function() {
				$scope.isAuthenticated = false;
				appConstants.clearAll();
				$scope.menus = [];
				$location.replace();
				$location.path('/login');
			}
			

      $scope.$on('loginStatusChanged', function () {
        $scope.isAuthenticated = appConstants.isAuthenticated();
        if($scope.isAuthenticated) {
          $http.defaults.headers.common['X-Auth-Token'] = appConstants.getItem('token');
        }
      });

		   $scope.$on('$routeChangeSuccess', function(event, next, current) {
		   		var path = $location.path();
		   		var currentPath = path.substring(1, path.length);
		   		$scope.selectedTab = getCurrentTab(currentPath);
		  	});

		}
	};
})
.config( function($routeProvider) {
   $routeProvider
      .when('/dashboard', {
        templateUrl : 'dashboard/dashboard.html',
        css: 'dashboard/dashboard.css',
        controller  : 'dashboardCtrl'
      })
      .when('/sprint', {
        templateUrl : 'sprint/sprint.html',
        css: 'sprint/sprint.css',
        controller  : 'sprintCtrl'
      })
      .when('/story', {
        templateUrl: 'story/story.html',
        css: 'story/story.css',
        controller: 'storyCtrl'
      })
      .when('/subtask', {
        templateUrl : 'subtask/subtask.html',
        css: 'subtask/subtask.css',
        controller  : 'subtaskCtrl'
      })
      .when('/efforts', {
        templateUrl : 'efforts/efforts.html',
        css: 'efforts/efforts.css',
        controller  : 'effortsCtrl'
      })
      .when('/leave', {
        templateUrl : 'leave/leave.html',
        css: 'leave/leave.css',
        controller  : 'leaveCtrl'
      })
      .when('/users', {
        templateUrl : 'user/user.html',
        css: 'user/user.css',
        controller  : 'userCtrl'
      })
      .when('/codereview', {
        templateUrl : 'codereview/codereview.html',
        css: 'codereview/codereview.css',
        controller  : 'codereviewCtrl'
      })

      .otherwise({
        redirectTo: '/dashboard'
      });
    });
});    
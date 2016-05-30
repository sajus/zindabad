'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', [])
	// .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		
	// 	$routeProvider.when('/story', {
	// 		templateUrl: 'story/story.html',
	// 		css: 'story/story.css',
	// 		controller: 'storyCtrl'
	// 	});
	// }])
	.controller('storyCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['story/storyCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
	
});


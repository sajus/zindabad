'use strict';

define([
	'angular',
	'angularRoute',
	'common/bootstrap',
	'angularLocalStorage',
], function(angular, angularRoute, bootstrap) {
	// Declare app level module which depends on views, and components
	var app = angular.module('myApp', [
		'ngRoute',
		'angularCSS',
		'LocalStorageModule'
	]).
	config([
		'$routeProvider',
		'$locationProvider',
		'$httpProvider',
		'$controllerProvider',
		'$compileProvider',
		'$filterProvider',
		'$provide',
		'$injector',
		'localStorageServiceProvider',
		function ($routeProvider, $locationProvider, $httpProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $injector, localStorageServiceProvider) {
			app.controller = $controllerProvider.register;
			app.directive = $compileProvider.directive;
			app.filter = $filterProvider.register;
			app.factory = $provide.factory;
			app.service = $provide.service;
			//localStorageServiceProvider.setPrefix('myApp');

			$routeProvider.otherwise({redirectTo: '/admin'});
			$locationProvider.html5Mode({
			  enabled: true,
			  reqireBase: false
			});

			//Lazy loading config
			var providers = {
				'$controllerProvider': $controllerProvider,
				'$compileProvider': $compileProvider,
				'$filterProvider': $filterProvider,
				'$provide': $provide,
				'$injector': $injector,
			};

			angular.forEach(bootstrap, function (module) {
				angular.forEach(module._invokeQueue, function (invokeArgs) { 
					var provider = providers[invokeArgs[0]];
					provider[invokeArgs[1]].apply(provider, invokeArgs[2]);
				});
				angular.forEach(module._configBlocks, function (invokeArgs) {
					var provider = providers[invokeArgs[0]];
					provider[invokeArgs[1]].apply(provider, invokeArgs[2]);
				});
				angular.forEach(module._runBlocks, function (invokeArgs) {
					var provider = providers[invokeArgs[0]];
					provider[invokeArgs[1]].apply(provider, invokeArgs[2]);
				});
			});
	}])

	.constant('appConstants', {
		url : 'http://localhost:8080/TaskManagement/',
		config :{
			headers: {
				'Context-Type' : 'application/json'
			}
		}
	})

	.run(function ($rootScope, $http, $location, localStorageService) {

		if (localStorageService.get('currentUser')) {
            $http.defaults.headers.common['X-Auth-Token'] = localStorageService.get('token');
        }

        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !localStorageService.get('currentUser')) {
                $location.path('/login');
             } 
        });

     });   

	return app;
});


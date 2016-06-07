'use strict';

define([
	'angular',
	'angularRoute',
	'common/bootstrap',
	'common/ga',
	'angularLocalStorage'
], function(angular, angularRoute, bootstrap, ga) {
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

			$routeProvider.otherwise({redirectTo: '/dashboard'});

			// $locationProvider.hashPrefix('!');
			//$locationProvider.html5Mode(true);

			// $locationProvider.html5Mode({
			//     enabled: true,
			//     requireBase: false
			// });
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


	.run(function ($rootScope, $templateCache, $http, $location, appConstants) {
		
		if (appConstants.isAuthenticated()) {
            $http.defaults.headers.common['X-Auth-Token'] = appConstants.getItem('token');
        }

        $rootScope.$on('$viewContentLoaded', function() {
	       $templateCache.removeAll();
	   	});

		ga.sendPageView('testUser', 'testPage');

        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	if (typeof(current) !== 'undefined'){
	            $templateCache.remove(current.templateUrl);
	        }
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !appConstants.isAuthenticated()) {
                $location.path('/login');
             } 


        });

     });   

	return app;
});


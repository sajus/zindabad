'use strict';

define([
	'angular',
	'angularRoute',
	'angularUiBootstrap',
	'angularResource',
	'common/bootstrap',
	'common/ga',
	'angularLocalStorage',
	'bootstrapDatepicker',
	'angularNvd3'
], function(angular, angularRoute, angularUiBootstrap, angularResource, bootstrap, ga, bootstrapDatepicker, angularNvd3) {
	// Declare app level module which depends on views, and components

	var app = angular.module('myApp', [
		'ngRoute',
		'ui.bootstrap',
		'ngResource',
		'angularCSS',
		'LocalStorageModule',
		'nvd3'
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
		'$resourceProvider',
		function ($routeProvider, $locationProvider, $httpProvider, $controllerProvider, $compileProvider, $filterProvider, 
			$provide, $injector, localStorageServiceProvider, $resourceProvider) {
			app.controller = $controllerProvider.register;
			app.directive = $compileProvider.directive;
			app.filter = $filterProvider.register;
			app.factory = $provide.factory;
			app.service = $provide.service;

			$routeProvider.otherwise({redirectTo: '/dashboard'});

			$resourceProvider.defaults.stripTrailingSlashes = false;

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


'use strict';

define([
	'angular',
	'angularRoute',
	'common/bootstrap'
], function(angular, angularRoute, bootstrap) {
	// Declare app level module which depends on views, and components
	var app = angular.module('myApp', [
		'ngRoute',
		'angularCSS'
	]).
	config([
		'$routeProvider',
		'$controllerProvider',
		'$compileProvider',
		'$filterProvider',
		'$provide',
		'$injector',
		function ($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $injector) {
			app.controller = $controllerProvider.register;
			app.directive = $compileProvider.directive;
			app.filter = $filterProvider.register;
			app.factory = $provide.factory;
			app.service = $provide.service;

			$routeProvider.otherwise({redirectTo: '/login'});

			//Lazy loading config
			var providers = {
				'$controllerProvider': $controllerProvider,
				'$compileProvider': $compileProvider,
				'$filterProvider': $filterProvider,
				'$provide': $provide,
				'$injector': $injector
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
	}]);

	return app;
});


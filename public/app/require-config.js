'use strict';

if(window.__karma__) {
	var allTestFiles = [];
	var TEST_REGEXP = /spec\.js$/;

	var pathToModule = function(path) {
		return path.replace(/^\/base\/app\//, '').replace(/\.js$/, '');
	};

	Object.keys(window.__karma__.files).forEach(function(file) {
		if (TEST_REGEXP.test(file)) {
			// Normalize paths to RequireJS module names.
			allTestFiles.push(pathToModule(file));
		}
	});
}

require.config({
	paths: {
		jQuery: 'bower_components/jquery/dist/jquery.min',
		angular: 'bower_components/angular/angular',
		angularRoute: 'bower_components/angular-route/angular-route',
		angularMocks: 'bower_components/angular-mocks/angular-mocks',
		angularLocalStorage: 'bower_components/angular-local-storage/dist/angular-local-storage.min',
		angularResource: 'bower_components/angular-resource/angular-resource.min',
		bootstrap: 'bower_components/bootstrap/dist/js/bootstrap.min',
		angularUiBootstrap: 'bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
		bootstrapDatepicker: 'bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min',
		underscore: 'bower_components/underscore/underscore-min',
		text: 'bower_components/requirejs-text/text',
		css: 'bower_components/angular-css/angular-css'
	},
	shim: {
		'angular' : {'exports' : 'angular'},
		'angularRoute': ['angular'],
		'angularMocks': {
			deps:['angular'],
			'exports':'angular.mock'
		},
		'angularLocalStorage': {
			deps: ['angular']
		},
		'jQuery': {
			exports: 'jQuery'
		},
		'underscore' : {
			exports: 'underscore'
		},
		'css': ['angular'],
		'angularResource': {
			deps: ['angular']
		},
		'angularUiBootstrap': {
			deps: ['angular']
		},
		'bootstrap': {		
 			exports: 'bootstrap',		
 			deps: ['jQuery']		
 		},

	},
	priority: [
		"angular"
	],
	deps: window.__karma__ ? allTestFiles : [],
	callback: window.__karma__ ? window.__karma__.start : null,
	baseUrl: window.__karma__ ? '/base/app' : '',
});

require([
	'angular',
	'app',
	'jQuery',
	'underscore',
	'bootstrap',
	], function(angular, app) {
		var $html = angular.element(document.getElementsByTagName('html')[0]);
		angular.element().ready(function() {
			// bootstrap the app manually
			angular.bootstrap(document, ['myApp']);
		});
	}
);
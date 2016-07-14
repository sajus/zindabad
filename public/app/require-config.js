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
		bootstrapDatepicker: 'bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min',
		angular: 'bower_components/angular/angular',	
		angularRoute: 'bower_components/angular-route/angular-route',
		angularMocks: 'bower_components/angular-mocks/angular-mocks',
		angularLocalStorage: 'bower_components/angular-local-storage/dist/angular-local-storage.min',
		angularResource: 'bower_components/angular-resource/angular-resource.min',
		tBootstrap: 'bower_components/bootstrap/dist/js/bootstrap.min',
		angularUiBootstrap: 'bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
		underscore: 'bower_components/underscore/underscore-min',
		angularNvd3: 'bower_components/angular-nvd3/dist/angular-nvd3.min',
		d3: 'bower_components/d3/d3.min',
		nvD3: 'bower_components/nvd3/build/nv.d3.min',
		text: 'bower_components/requirejs-text/text',
		css: 'bower_components/angular-css/angular-css'
	},
	shim: {
		'jQuery': {
			exports: 'jQuery'
		},
		'tBootstrap': {
		  exports: 'tBootstrap',
      deps: ['jQuery']
		},
		'angularUiBootstrap': {
			deps: ['jQuery', 'tBootstrap']
		},
		'angular' : {'exports' : 'angular'},
		'angularResource': {
			deps: ['angular']
		},
		'angularRoute': ['angular'],
		'angularMocks': {
			deps:['angular'],
			'exports':'angular.mock'
		},
		'angularLocalStorage': {
			deps: ['angular']
		},
		'underscore' : {
			exports: 'underscore'
		},
		'css': ['angular'],
		'd3': ['angular'],
		'nvD3': {
			deps: ['d3']
		},
		'bootstrapDatepicker': {
			deps: ['jQuery', 'angular']
		},
		'angularNvd3': {
			deps: ['angular', 'nvD3', 'd3']
		}
	},
	priority: [
		"jQuery",
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
	'tBootstrap'
	], function(angular, app) {
		var $html = angular.element(document.getElementsByTagName('html')[0]);
		angular.element().ready(function() {
			// bootstrap the app manually
			angular.bootstrap(document, ['myApp']);
		});
	}
);
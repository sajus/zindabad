// Todo


//http://bootsnipp.com/snippets/featured/form-wizard-using-tabs
//http://bootsnipp.com/snippets/featured/content-in-bootstrap-tabs
//http://bootsnipp.com/snippets/l02o0 // circular buttons
//http://bootsnipp.com/snippets/featured/panel-table-with-filters-per-column    //tables
//http://bootsnipp.com/snippets/z4K4d  //table

// /http://bootsnipp.com/snippets/kE54g //circular progressbar

//http://bootsnipp.com/snippets/d0nrN // button styles
//http://bootsnipp.com/snippets/z4K4d

//http://bootsnipp.com/snippets/b4mgg  // loading...

//http://bootsnipp.com/snippets/xaZaG (fancy form)


'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', [])
	.controller('subtaskCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['subtask/subtaskCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
	
});

'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',[]).directive('tmsTable', function () {
	return {
		restrict: 'E',
		templateUrl: 'components/tmstable/tmsTable.html',
		$scope: {

		 },
		replace: false,
		controller: function($scope, $attrs, $element) {

			
		}
	};
});    	


});    
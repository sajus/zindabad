'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',[]).directive('appHeader', function () {
  return {
    restrict: 'EA',
    templateUrl: 'components/header/header.html',
    scope: { action: '='},
    replace: true,
    controller: function($scope, $attrs) {
    	//console.log("scope",$scope);
    }
  };
});    	


});    
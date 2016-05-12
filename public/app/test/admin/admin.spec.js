/*global module, inject */
'use strict';

define(['app', 'angularMocks'], function(app) {
	describe('myApp.admin module', function() {

		beforeEach(module('myApp.admin'));

		describe('admin controller', function(){
			it('should ....', inject(function($controller) {
				//spec body
				var adminCtrl = $controller('adminCtrl', { $scope: {} });
				expect(adminCtrl).toBeDefined();
			}));
		});
	});
});
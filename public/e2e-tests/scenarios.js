/* global describe, it, expect, beforeEach, afterEach, module, inject, browser, element, by */
'use strict';

/* https://github.com/angular/protractor/blob/master/docs/toc.md */

describe('my app', function() {

  browser.get('index.html');

  it('should automatically redirect to /login when location hash/fragment is empty', function() {
    expect(browser.getLocationAbsUrl()).toMatch("/login");
  });


  describe('login', function() {

    beforeEach(function() {
      browser.get('index.html#/login');
    });


    it('should render login when user navigates to /login', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 1/);
    });

  });


  describe('admin', function() {

    beforeEach(function() {
      browser.get('index.html#/admin');
    });


    it('should render admin when user navigates to /admin', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 2/);
    });

    it('should fire adminCtrl when user navigates to /admin', function() {
      expect(element.all(by.css('[ng-view] p:nth-child(2)')).first().getText()).
        toMatch(/hey this is ctrl2/);
    });
  });
});

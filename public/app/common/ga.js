define(function(require) {
    'use strict';
    var ga_code = 'UA-78899220-1'; //RACKING ID;
    var ga_domain = 'tms.cybage.com'; //SERVER DOMAIN;

    (function(i, s, o, g, r, a, m) {
        i.GoogleAnalyticsObject = r;
        i[r] = i[r] || function() {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
        m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

    ga('create', ga_code, ga_domain);

    var _sendPageView = function(clientname, pageName) {
        if (!ga) {
            return false;
        }
        ga('send', 'pageview', {
            'page': pageName + ' | ' + clientname,
            'hitCallback': function() {
                console.log('GoogleAnalytics:pageViewEvent ' + pageName + ' : ' + clientname);
            }
        });
        return true;
    };

    var _updateEfforts = function(clientname, previousTime, updatedTime) {
        if (!ga) {
            return false;
        }
        ga('send', {
            'hitType': 'event',
            'eventCategory': 'effortUpdate',
            'eventAction': 'update',
            'eventLabel': clientname + ' | Effort Update ' + previousTime + '-->' +updatedTime,
            'hitCallback': function() {
                console.log('GoogleAnalytics:updateEfforts:--> ');
            }
        });

        return true;
    };

    return {
        'updateEfforts': _updateEfforts,
        'sendPageView': _sendPageView
    };
});
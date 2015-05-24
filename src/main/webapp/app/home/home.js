'use strict';

angular.module('timesheet-app.home', ['ui.router']).

    config(function config($stateProvider) {

        $stateProvider
            .state('home', {
                url: '^/home',
                controller: 'HomeController',
                templateUrl: 'app/home/home.tpl.html'
            });
    });
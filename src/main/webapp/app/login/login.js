'use strict';

angular.module('timesheet-app.login', ['ui.router', 'ngCookies']).

    constant('loginConfig', {
       xAuthToken: 'x-auth-token'
    }).

    config(function config($stateProvider, $httpProvider) {

        $stateProvider
            .state('login', {
                url: '^/login',
                controller: 'LoginController',
                templateUrl: 'app/login/login.tpl.html'
            });
    }).run(function($rootScope, $http, $state, $cookieStore, Auth) {


        $rootScope.$on('$viewContentLoaded', function() {
            delete $rootScope.error;
        });

        $rootScope.$on('$stateChangeStart', function(event, toState) {
            if (toState.data !== undefined && toState.data.authenticate !== undefined) {
                if (toState.data.authenticate && !$rootScope.isAuthenticated){
                    $state.transitionTo('login');
                    event.preventDefault();
                }
            }
        });

        var authToken = $cookieStore.get('authToken');
        if(authToken !== undefined) {
            $rootScope.authToken = authToken;
            $http.defaults.headers.common['x-auth-token'] = authToken;
            Auth.user.get(function(user) {
                if(user.token != undefined) {
                    $rootScope.isAuthenticated = true;
                    $state.transitionTo('home');
                } else {
                    $rootScope.isAuthenticated = false;
                    $state.transitionTo('login');
                }
            });
        }


    });
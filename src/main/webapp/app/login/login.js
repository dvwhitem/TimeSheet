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

        var interceptor = function ($rootScope, $q, $location) {

            function success(response) {
                return response;
            }

            function error(response) {

                var status = response.status;
                var config = response.config;
                var method = config.method;
                var url = config.url;

                if (status === 401) {
                    $location.path('/login');
                } else {
                    $rootScope.error = method + ' on ' + url + ' failed with status ' + status;
                }

                return $q.reject(response);
            }

            return function (promise) {
                return promise.then(success, error);
            };
        };
        $httpProvider.interceptors.push(interceptor);


    }).run(function($rootScope, $http, $state, $cookieStore, loginConfig, $log) {

        $rootScope.$on('$viewContentLoaded', function() {
            delete $rootScope.error;
        });

        $rootScope.$on('$stateChangeStart', function(event, toState) {
            if (toState.data !== undefined && toState.data.authenticate !== undefined) {
                $log.info('Need to authenticate', toState);
                $log.info('User authenticated?', $rootScope.isAuthenticated);

                if (toState.data.authenticate && !$rootScope.isAuthenticated){
                    $log.info('Setting desired state to ' + toState.name);
                    $rootScope.desiredToState = toState.name;
                    $state.transitionTo('login');
                    event.preventDefault();
                }
            }
        });
        $rootScope.hasRole = function(role) {

            if (!$rootScope.isAuthenticated) {
                return false;
            }
            if ($rootScope.user.roles[role] === undefined) {
                return false;
            }
            return $rootScope.user.roles[role];
        }

        var user = $cookieStore.get('user');
        if(user.token) {
            $rootScope.isAuthenticated = true;
            $rootScope.user = user;
            $http.defaults.headers.common[loginConfig.xAuthToken] = user.token;
        }
    });
'use strict';

angular.module('timesheet-app.login').

    controller('LoginController', function ($rootScope, $state, $scope, $http, $cookieStore, loginConfig, Auth) {

        var authenticate = function(credentials, callback) {
            $scope.login = Auth.login.save(credentials, function(user) {
                if($rootScope.user.token) {
                    console.log('USER TOKEN : ' + user.token);
                    $rootScope.user = user;
                    $rootScope.isAuthenticated = true;
                    $http.defaults.headers.common[loginConfig.xAuthToken] = user.token;
                    $cookieStore.put('user', user);
                } else {
                    $rootScope.isAuthenticated = false;
                }
                callback && callback($rootScope.isAuthenticated);
            });
        }


        $scope.credentials = {}

        $scope.login = function() {
            authenticate($scope.credentials, function(isAuthenticated) {
               if(isAuthenticated) {
                   console.log("Login success");
                   $state.go('home');
               }  else {
                   console.log("Login failed");
               }
            });
        }
    });
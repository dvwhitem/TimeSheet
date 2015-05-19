'use strict';

var welcomeController = angular.module('app.welcomeController', []);

welcomeController.controller('WelcomeController', ['$scope', function ($scope) {
    $scope.welcome = 'Welcome to Timesheet';
}]);

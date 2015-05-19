'use strict';

var app = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'app.employeeController',
    'app.welcomeController',
    'app.employeeService'
]);

app.config(['$routeProvider',
function($routeProvider) {

    $routeProvider
        .when('/employee/:id', {
        templateUrl: 'partials/employee-detail.html',
        controller: 'EmployeeDetailController'
    })
        .when('/employees/:pageNumber?', {
        templateUrl: 'partials/employee-list.html',
        controller: 'EmployeeListController'
    });

    $routeProvider.otherwise({
        templateUrl: 'partials/welcome.html',
        controller: 'WelcomeController'
    })
}]);





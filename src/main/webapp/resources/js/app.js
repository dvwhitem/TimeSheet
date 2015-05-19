'use strict';

var app = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'employeeControllers',
    'employeeServices'
]);

app.config(['$routeProvider',
function($routeProvider) {

    $routeProvider.when('/employee/:id', {
        templateUrl: 'partials/employee-detail.html',
        controller: 'EmployeeDetailController'
    });

    $routeProvider.otherwise({
        templateUrl: 'partials/employee-list.html',
        controller: 'EmployeeListController'
    });
}]);





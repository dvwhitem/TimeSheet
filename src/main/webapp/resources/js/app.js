'use strict';
var app = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'employeeControllers',
    'employeeServices',
    'taskControllers',
    'taskServices',
    'timesheetControllers',
    'timesheetServices'
]);
app.config(['$routeProvider',
    function ($routeProvider) {

        $routeProvider.when('/employee/:id', {
            templateUrl: 'partials/employee-detail.html',
            controller: 'EmployeeDetailController'
        })
            .when('/employees/:pageNumber?', {
                templateUrl: 'partials/employee-list.html',
                controller: 'EmployeeListController'
            });



        $routeProvider.when('/manager/:id', {
            templateUrl: 'partials/manager-detail.html',
            controller: 'ManagerDetailController'
        })
            .when('/managers/:pageNumber?', {
                templateUrl: 'partials/employee-list.html',
                controller: 'ManagerListController'
            });


        $routeProvider.when('/task/:id', {
            templateUrl: 'partials/task-detail.html',
            controller: 'TaskDetailController'
        })
            .when('/tasks/:pageNumber?', {
                templateUrl: 'partials/task-list.html',
                controller: 'TaskListController'
            })

            .when('/task/add', {
                templateUrl: 'partials/task-create.html',
                controller: 'TaskAddController'
            })
            .when('/taskedit/:id', {
                templateUrl: 'partials/task-edit.html',
                controller: 'TaskEditController'
            });



        $routeProvider.when('/timesheet/:id', {
            templateUrl: 'partials/timesheet-detail.html',
            controller: 'TimesheetDetailController'
        })
            .when('/timesheets/:pageNumber?', {
                templateUrl: 'partials/timesheet-list.html',
                controller: 'TimesheetListController'
            });


        $routeProvider.otherwise({
            templateUrl: 'partials/welcome.html',
            controller: 'WelcomeController'
        });


    }]);

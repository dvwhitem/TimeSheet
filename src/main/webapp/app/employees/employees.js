'use strict';

angular.module('timesheet-app.employees', ['ui.router']).

    config(function config ($stateProvider) {

        $stateProvider
            .state('employees', {
                url: '^/employees',
                templateUrl: 'app/employees/employees.tpl.html',
                controller: 'EmployeesController',
                data: {
                    authenticate: true
                }
            })
            .state('paginated', {
                url: '^/employees/:pageNumber',
                templateUrl: 'app/employees/employees.tpl.html',
                controller: 'EmployeesController',
                data: {
                    authenticate: true
                }
            })
            .state('detail', {
                url: '^/employee/:id',
                templateUrl: 'app/employees/employees-detail.tpl.html',
                controller: 'EmployeeDetailController',
                data: {
                    authenticate: true
                }
            });
    });
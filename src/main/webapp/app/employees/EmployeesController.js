'use strict';

angular.module('timesheet-app.employees').

    controller('EmployeesController',
    function ($scope, $stateParams, Employee) {
        if (!$stateParams.pageNumber) $stateParams.pageNumber = 1;
        /* page properties */
        $scope.records = [];
        $scope.totalPages = 0;
        $scope.currentPage = 1;
        $scope.range = [];
        $scope.totalRecords = 0;
        $scope.data = {};

        /**
         * Get all records with arguments
         * @param pageNumber
         */
        $scope.getAllRecords = function () {
            $scope.data = Employee.getAllEmployees.query({pageNumber: $stateParams.pageNumber}, function (employee) {
                $scope.records = employee.content;
                $scope.totalPages = employee.totalPages;
                $scope.currentPage = employee.number + 1;
                $scope.totalRecords = employee.totalElements;

                var pages = [];
                for (var i = 1; i <= employee.totalPages; i++) {
                    pages.push(i);
                }
                $scope.range = pages;
            });
        };
        /* if don't there previous element return start page */
        $scope.noPrevious = function () {
            return $scope.currentPage == 1;
        };
        /* if there previous element return it */
        $scope.selectPrevious = function () {
            if (!$scope.noPrevious()) {
                return $scope.currentPage - 1;
            }
            return $scope.currentPage;
        };
        /* if don't there next element return start page */
        $scope.noNext = function () {
            return $scope.currentPage == $scope.totalPages;
        };
        /* if there next element return it */
        $scope.selectNext = function () {
            if (!$scope.noNext()) {
                return $scope.currentPage + 1;
            }
            return $scope.currentPage;
        }
        /* return is active page */
        $scope.isActive = function (page) {
            return $scope.currentPage === page;

        };
        /* get default all records */
        $scope.getAllRecords(1);
    }).

    controller('EmployeeDetailController',
    function ($scope, $state, Employee) {
        $scope.employee = Employee.getEmployeeById.get({id: $state.params.id});
    });
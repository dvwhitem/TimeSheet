'use strict';

var app = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'employeeControllers',
    'employeeServices',
    'taskControllers',
    'taskServices'
]);



app.config(['$routeProvider',
function($routeProvider) {


    $routeProvider.when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'LoginController'
    });

    $routeProvider.when('/employee/:id', {
        templateUrl: 'partials/employee-detail.html',
        controller: 'EmployeeDetailController'
    });


    $routeProvider.when('/tasks', {
        templateUrl: 'partials/task-list.html',
        controller: 'TaskController'
    });

    $routeProvider.when('/taskcreate', {
        templateUrl: 'partials/task-create.html',
        controller: 'TaskCreateController'
    });

    $routeProvider.when('/taskedit/:id', {
        templateUrl: 'partials/task-edit.html',
        controller: 'TaskEditController'
    });

    $routeProvider.otherwise({
        templateUrl: 'partials/employee-list.html',
        controller: 'EmployeeListController'
    });


app.run(function($rootScope, $location, $cookieStore, UserService) {

    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
        delete $rootScope.error;
    });

    $rootScope.hasRole = function(role) {

        if ($rootScope.user === undefined) {
            return false;
        }

        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }

        return $rootScope.user.roles[role];
    };

    $rootScope.logout = function() {
        delete $rootScope.user;
        delete $rootScope.authToken;
        $cookieStore.remove('authToken');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */
    var originalPath = $location.path();
    $location.path("/login");
    var authToken = $cookieStore.get('authToken');
    if (authToken !== undefined) {
        $rootScope.authToken = authToken;
        UserService.get(function(user) {
            $rootScope.user = user;
            $location.path(originalPath);
        });
    }

    $rootScope.initialized = true;
});




}]);





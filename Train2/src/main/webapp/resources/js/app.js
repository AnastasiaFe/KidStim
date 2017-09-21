'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.service', 'AngularSpringApp.directives', 'ngRoute', 'ui.bootstrap', 'ngTable', 'ui.ace', 'angularFileUpload', 'nvd3ChartDirectives']);

// Declare app level module which depends on filters, and service
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/todo', {
        templateUrl: 'todo/layout',
        controller: TodoController
    });
    $routeProvider.when('/address', {
        templateUrl: 'address/layout',
        controller: AddressBookController
    });
    $routeProvider.when('/table', {
        templateUrl: 'table/layout',
        controller: TableController
    });
    $routeProvider.when('/tabs', {
        templateUrl: 'tabs/layout',
        controller: TabsController
    })


    $routeProvider.otherwise({redirectTo: '/todo'});
}]);

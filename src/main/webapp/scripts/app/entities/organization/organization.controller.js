'use strict';

angular.module('refugeesApp')
    .controller('OrganizationController', function ($scope, $state, Organization) {

        $scope.organizations = [];
        $scope.loadAll = function() {
            Organization.query(function(result) {
               $scope.organizations = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.organization = {
                name: null,
                phone: null,
                email: null,
                address: null,
                id: null
            };
        };
    });

'use strict';

angular.module('refugeesApp')
    .controller('OrganizationTypeController', function ($scope, $state, OrganizationType) {

        $scope.organizationTypes = [];
        $scope.loadAll = function() {
            OrganizationType.query(function(result) {
               $scope.organizationTypes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.organizationType = {
                name: null,
                id: null
            };
        };
    });

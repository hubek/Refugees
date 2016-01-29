'use strict';

angular.module('refugeesApp')
    .controller('OrganizationDetailController', function ($scope, $rootScope, $stateParams, entity, Organization, OrganizationType) {
        $scope.organization = entity;
        $scope.load = function (id) {
            Organization.get({id: id}, function(result) {
                $scope.organization = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:organizationUpdate', function(event, result) {
            $scope.organization = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

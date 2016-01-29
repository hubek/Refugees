'use strict';

angular.module('refugeesApp')
	.controller('OrganizationDeleteController', function($scope, $uibModalInstance, entity, Organization) {

        $scope.organization = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Organization.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

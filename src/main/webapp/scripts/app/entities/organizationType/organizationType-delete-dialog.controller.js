'use strict';

angular.module('refugeesApp')
	.controller('OrganizationTypeDeleteController', function($scope, $uibModalInstance, entity, OrganizationType) {

        $scope.organizationType = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OrganizationType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

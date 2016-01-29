'use strict';

angular.module('refugeesApp')
	.controller('BranchDeleteController', function($scope, $uibModalInstance, entity, Branch) {

        $scope.branch = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Branch.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

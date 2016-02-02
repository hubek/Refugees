'use strict';

angular.module('refugeesApp')
	.controller('DemandDeleteController', function($scope, $uibModalInstance, entity, Demand) {

        $scope.demand = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Demand.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

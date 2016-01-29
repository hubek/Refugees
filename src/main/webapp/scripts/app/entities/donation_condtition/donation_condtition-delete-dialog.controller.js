'use strict';

angular.module('refugeesApp')
	.controller('Donation_condtitionDeleteController', function($scope, $uibModalInstance, entity, Donation_condtition) {

        $scope.donation_condtition = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Donation_condtition.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

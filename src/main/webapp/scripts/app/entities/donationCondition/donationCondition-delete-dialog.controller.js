'use strict';

angular.module('refugeesApp')
	.controller('DonationConditionDeleteController', function($scope, $uibModalInstance, entity, DonationCondition) {

        $scope.donationCondition = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DonationCondition.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

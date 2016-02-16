'use strict';

angular.module('refugeesApp')
	.controller('OfferDeleteController', function($scope, $uibModalInstance, entity, Offer) {

        $scope.offer = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Offer.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

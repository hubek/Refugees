'use strict';

angular.module('refugeesApp')
	.controller('OpeningHourDeleteController', function($scope, $uibModalInstance, entity, OpeningHour) {

        $scope.openingHour = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OpeningHour.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

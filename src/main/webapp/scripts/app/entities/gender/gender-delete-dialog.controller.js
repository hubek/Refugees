'use strict';

angular.module('refugeesApp')
	.controller('GenderDeleteController', function($scope, $uibModalInstance, entity, Gender) {

        $scope.gender = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Gender.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

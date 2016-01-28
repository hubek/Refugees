'use strict';

angular.module('refugeesApp')
	.controller('SizeDeleteController', function($scope, $uibModalInstance, entity, Size) {

        $scope.size = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Size.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

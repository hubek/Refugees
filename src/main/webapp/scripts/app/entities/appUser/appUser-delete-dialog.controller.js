'use strict';

angular.module('refugeesApp')
	.controller('AppUserDeleteController', function($scope, $uibModalInstance, entity, AppUser) {

        $scope.appUser = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            AppUser.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });

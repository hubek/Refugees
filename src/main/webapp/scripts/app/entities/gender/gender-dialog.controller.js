'use strict';

angular.module('refugeesApp').controller('GenderDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Gender',
        function($scope, $stateParams, $uibModalInstance, entity, Gender) {

        $scope.gender = entity;
        $scope.load = function(id) {
            Gender.get({id : id}, function(result) {
                $scope.gender = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:genderUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.gender.id != null) {
                Gender.update($scope.gender, onSaveSuccess, onSaveError);
            } else {
                Gender.save($scope.gender, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

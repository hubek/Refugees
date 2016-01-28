'use strict';

angular.module('refugeesApp').controller('OrganizationTypeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrganizationType',
        function($scope, $stateParams, $uibModalInstance, entity, OrganizationType) {

        $scope.organizationType = entity;
        $scope.load = function(id) {
            OrganizationType.get({id : id}, function(result) {
                $scope.organizationType = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:organizationTypeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.organizationType.id != null) {
                OrganizationType.update($scope.organizationType, onSaveSuccess, onSaveError);
            } else {
                OrganizationType.save($scope.organizationType, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

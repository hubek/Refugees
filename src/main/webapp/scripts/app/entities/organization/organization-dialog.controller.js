'use strict';

angular.module('refugeesApp').controller('OrganizationDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Organization', 'OrganizationType',
        function($scope, $stateParams, $uibModalInstance, entity, Organization, OrganizationType) {

        $scope.organization = entity;
        $scope.organizationtypes = OrganizationType.query();
        $scope.load = function(id) {
            Organization.get({id : id}, function(result) {
                $scope.organization = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:organizationUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.organization.id != null) {
                Organization.update($scope.organization, onSaveSuccess, onSaveError);
            } else {
                Organization.save($scope.organization, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

'use strict';

angular.module('refugeesApp').controller('BranchDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Branch', 'Organization',
        function($scope, $stateParams, $uibModalInstance, entity, Branch, Organization) {

        $scope.branch = entity;
        $scope.organizations = Organization.query();
        $scope.load = function(id) {
            Branch.get({id : id}, function(result) {
                $scope.branch = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:branchUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.branch.id != null) {
                Branch.update($scope.branch, onSaveSuccess, onSaveError);
            } else {
                Branch.save($scope.branch, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

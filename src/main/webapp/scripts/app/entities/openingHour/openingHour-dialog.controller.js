'use strict';

angular.module('refugeesApp').controller('OpeningHourDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OpeningHour', 'Branch',
        function($scope, $stateParams, $uibModalInstance, entity, OpeningHour, Branch) {

        $scope.openingHour = entity;
        $scope.branchs = Branch.query();
        $scope.load = function(id) {
            OpeningHour.get({id : id}, function(result) {
                $scope.openingHour = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:openingHourUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.openingHour.id != null) {
                OpeningHour.update($scope.openingHour, onSaveSuccess, onSaveError);
            } else {
                OpeningHour.save($scope.openingHour, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

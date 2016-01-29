'use strict';

angular.module('refugeesApp').controller('Donation_condtitionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Donation_condtition',
        function($scope, $stateParams, $uibModalInstance, entity, Donation_condtition) {

        $scope.donation_condtition = entity;
        $scope.load = function(id) {
            Donation_condtition.get({id : id}, function(result) {
                $scope.donation_condtition = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:donation_condtitionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.donation_condtition.id != null) {
                Donation_condtition.update($scope.donation_condtition, onSaveSuccess, onSaveError);
            } else {
                Donation_condtition.save($scope.donation_condtition, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

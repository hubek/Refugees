'use strict';

angular.module('refugeesApp').controller('DonationConditionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DonationCondition',
        function($scope, $stateParams, $uibModalInstance, entity, DonationCondition) {

        $scope.donationCondition = entity;
        $scope.load = function(id) {
            DonationCondition.get({id : id}, function(result) {
                $scope.donationCondition = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:donationConditionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.donationCondition.id != null) {
                DonationCondition.update($scope.donationCondition, onSaveSuccess, onSaveError);
            } else {
                DonationCondition.save($scope.donationCondition, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

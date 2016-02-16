'use strict';

angular.module('refugeesApp').controller('OfferDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Offer', 'Category', 'Season', 'Size', 'DonationCondition', 'Gender', 'Organization', 'Status',
        function($scope, $stateParams, $uibModalInstance, entity, Offer, Category, Season, Size, DonationCondition, Gender, Organization, Status) {

        $scope.offer = entity;
        $scope.categorys = Category.query();
        $scope.seasons = Season.query();
        $scope.sizes = Size.query();
        $scope.donationconditions = DonationCondition.query();
        $scope.genders = Gender.query();
        $scope.organizations = Organization.query();
        $scope.statuss = Status.query();
        $scope.load = function(id) {
            Offer.get({id : id}, function(result) {
                $scope.offer = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:offerUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.offer.id != null) {
                Offer.update($scope.offer, onSaveSuccess, onSaveError);
            } else {
                Offer.save($scope.offer, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);

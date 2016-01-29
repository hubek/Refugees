'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('donationCondition', {
                parent: 'entity',
                url: '/donationConditions',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.donationCondition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/donationCondition/donationConditions.html',
                        controller: 'DonationConditionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('donationCondition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('donationCondition.detail', {
                parent: 'entity',
                url: '/donationCondition/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.donationCondition.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/donationCondition/donationCondition-detail.html',
                        controller: 'DonationConditionDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('donationCondition');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'DonationCondition', function($stateParams, DonationCondition) {
                        return DonationCondition.get({id : $stateParams.id});
                    }]
                }
            })
            .state('donationCondition.new', {
                parent: 'donationCondition',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donationCondition/donationCondition-dialog.html',
                        controller: 'DonationConditionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    value: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('donationCondition', null, { reload: true });
                    }, function() {
                        $state.go('donationCondition');
                    })
                }]
            })
            .state('donationCondition.edit', {
                parent: 'donationCondition',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donationCondition/donationCondition-dialog.html',
                        controller: 'DonationConditionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DonationCondition', function(DonationCondition) {
                                return DonationCondition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('donationCondition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('donationCondition.delete', {
                parent: 'donationCondition',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donationCondition/donationCondition-delete-dialog.html',
                        controller: 'DonationConditionDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DonationCondition', function(DonationCondition) {
                                return DonationCondition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('donationCondition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

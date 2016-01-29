'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('donation_condtition', {
                parent: 'entity',
                url: '/donation_condtitions',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.donation_condtition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/donation_condtition/donation_condtitions.html',
                        controller: 'Donation_condtitionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('donation_condtition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('donation_condtition.detail', {
                parent: 'entity',
                url: '/donation_condtition/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.donation_condtition.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/donation_condtition/donation_condtition-detail.html',
                        controller: 'Donation_condtitionDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('donation_condtition');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Donation_condtition', function($stateParams, Donation_condtition) {
                        return Donation_condtition.get({id : $stateParams.id});
                    }]
                }
            })
            .state('donation_condtition.new', {
                parent: 'donation_condtition',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donation_condtition/donation_condtition-dialog.html',
                        controller: 'Donation_condtitionDialogController',
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
                        $state.go('donation_condtition', null, { reload: true });
                    }, function() {
                        $state.go('donation_condtition');
                    })
                }]
            })
            .state('donation_condtition.edit', {
                parent: 'donation_condtition',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donation_condtition/donation_condtition-dialog.html',
                        controller: 'Donation_condtitionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Donation_condtition', function(Donation_condtition) {
                                return Donation_condtition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('donation_condtition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('donation_condtition.delete', {
                parent: 'donation_condtition',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/donation_condtition/donation_condtition-delete-dialog.html',
                        controller: 'Donation_condtitionDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Donation_condtition', function(Donation_condtition) {
                                return Donation_condtition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('donation_condtition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

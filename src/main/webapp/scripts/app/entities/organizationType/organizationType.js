'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('organizationType', {
                parent: 'entity',
                url: '/organizationTypes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.organizationType.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/organizationType/organizationTypes.html',
                        controller: 'OrganizationTypeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('organizationType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('organizationType.detail', {
                parent: 'entity',
                url: '/organizationType/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.organizationType.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/organizationType/organizationType-detail.html',
                        controller: 'OrganizationTypeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('organizationType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'OrganizationType', function($stateParams, OrganizationType) {
                        return OrganizationType.get({id : $stateParams.id});
                    }]
                }
            })
            .state('organizationType.new', {
                parent: 'organizationType',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/organizationType/organizationType-dialog.html',
                        controller: 'OrganizationTypeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('organizationType', null, { reload: true });
                    }, function() {
                        $state.go('organizationType');
                    })
                }]
            })
            .state('organizationType.edit', {
                parent: 'organizationType',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/organizationType/organizationType-dialog.html',
                        controller: 'OrganizationTypeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OrganizationType', function(OrganizationType) {
                                return OrganizationType.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('organizationType', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('organizationType.delete', {
                parent: 'organizationType',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/organizationType/organizationType-delete-dialog.html',
                        controller: 'OrganizationTypeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OrganizationType', function(OrganizationType) {
                                return OrganizationType.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('organizationType', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

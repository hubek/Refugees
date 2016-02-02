'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('demand', {
                parent: 'entity',
                url: '/demands',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.demand.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/demand/demands.html',
                        controller: 'DemandController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('demand');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('demand.detail', {
                parent: 'entity',
                url: '/demand/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.demand.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/demand/demand-detail.html',
                        controller: 'DemandDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('demand');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Demand', function($stateParams, Demand) {
                        return Demand.get({id : $stateParams.id});
                    }]
                }
            })
            .state('demand.new', {
                parent: 'demand',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/demand/demand-dialog.html',
                        controller: 'DemandDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    quantity: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('demand', null, { reload: true });
                    }, function() {
                        $state.go('demand');
                    })
                }]
            })
            .state('demand.edit', {
                parent: 'demand',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/demand/demand-dialog.html',
                        controller: 'DemandDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Demand', function(Demand) {
                                return Demand.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('demand', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('demand.delete', {
                parent: 'demand',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/demand/demand-delete-dialog.html',
                        controller: 'DemandDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Demand', function(Demand) {
                                return Demand.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('demand', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

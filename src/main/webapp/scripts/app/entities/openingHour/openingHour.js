'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('openingHour', {
                parent: 'entity',
                url: '/openingHours',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.openingHour.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/openingHour/openingHours.html',
                        controller: 'OpeningHourController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('openingHour');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('openingHour.detail', {
                parent: 'entity',
                url: '/openingHour/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.openingHour.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/openingHour/openingHour-detail.html',
                        controller: 'OpeningHourDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('openingHour');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'OpeningHour', function($stateParams, OpeningHour) {
                        return OpeningHour.get({id : $stateParams.id});
                    }]
                }
            })
            .state('openingHour.new', {
                parent: 'openingHour',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/openingHour/openingHour-dialog.html',
                        controller: 'OpeningHourDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    day: null,
                                    open: null,
                                    close: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('openingHour', null, { reload: true });
                    }, function() {
                        $state.go('openingHour');
                    })
                }]
            })
            .state('openingHour.edit', {
                parent: 'openingHour',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/openingHour/openingHour-dialog.html',
                        controller: 'OpeningHourDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OpeningHour', function(OpeningHour) {
                                return OpeningHour.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('openingHour', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('openingHour.delete', {
                parent: 'openingHour',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/openingHour/openingHour-delete-dialog.html',
                        controller: 'OpeningHourDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OpeningHour', function(OpeningHour) {
                                return OpeningHour.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('openingHour', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

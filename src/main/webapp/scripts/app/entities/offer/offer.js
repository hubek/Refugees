'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('offer', {
                parent: 'entity',
                url: '/offers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.offer.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/offer/offers.html',
                        controller: 'OfferController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('offer');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('offer.detail', {
                parent: 'entity',
                url: '/offer/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.offer.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/offer/offer-detail.html',
                        controller: 'OfferDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('offer');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Offer', function($stateParams, Offer) {
                        return Offer.get({id : $stateParams.id});
                    }]
                }
            })
            .state('offer.new', {
                parent: 'offer',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/offer/offer-dialog.html',
                        controller: 'OfferDialogController',
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
                        $state.go('offer', null, { reload: true });
                    }, function() {
                        $state.go('offer');
                    })
                }]
            })
            .state('offer.edit', {
                parent: 'offer',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/offer/offer-dialog.html',
                        controller: 'OfferDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Offer', function(Offer) {
                                return Offer.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('offer', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('offer.delete', {
                parent: 'offer',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/offer/offer-delete-dialog.html',
                        controller: 'OfferDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Offer', function(Offer) {
                                return Offer.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('offer', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

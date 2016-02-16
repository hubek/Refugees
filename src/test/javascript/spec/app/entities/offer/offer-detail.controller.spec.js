'use strict';

describe('Controller Tests', function() {

    describe('Offer Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOffer, MockCategory, MockSeason, MockSize, MockDonationCondition, MockGender, MockOrganization;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOffer = jasmine.createSpy('MockOffer');
            MockCategory = jasmine.createSpy('MockCategory');
            MockSeason = jasmine.createSpy('MockSeason');
            MockSize = jasmine.createSpy('MockSize');
            MockDonationCondition = jasmine.createSpy('MockDonationCondition');
            MockGender = jasmine.createSpy('MockGender');
            MockOrganization = jasmine.createSpy('MockOrganization');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Offer': MockOffer,
                'Category': MockCategory,
                'Season': MockSeason,
                'Size': MockSize,
                'DonationCondition': MockDonationCondition,
                'Gender': MockGender,
                'Organization': MockOrganization
            };
            createController = function() {
                $injector.get('$controller')("OfferDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:offerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

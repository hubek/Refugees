'use strict';

describe('Controller Tests', function() {

    describe('DonationCondition Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDonationCondition;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDonationCondition = jasmine.createSpy('MockDonationCondition');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DonationCondition': MockDonationCondition
            };
            createController = function() {
                $injector.get('$controller')("DonationConditionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:donationConditionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

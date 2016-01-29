'use strict';

describe('Controller Tests', function() {

    describe('Donation_condtition Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDonation_condtition;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDonation_condtition = jasmine.createSpy('MockDonation_condtition');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Donation_condtition': MockDonation_condtition
            };
            createController = function() {
                $injector.get('$controller')("Donation_condtitionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:donation_condtitionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

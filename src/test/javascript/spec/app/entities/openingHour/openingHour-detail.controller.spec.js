'use strict';

describe('Controller Tests', function() {

    describe('OpeningHour Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOpeningHour, MockBranch;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOpeningHour = jasmine.createSpy('MockOpeningHour');
            MockBranch = jasmine.createSpy('MockBranch');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OpeningHour': MockOpeningHour,
                'Branch': MockBranch
            };
            createController = function() {
                $injector.get('$controller')("OpeningHourDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:openingHourUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

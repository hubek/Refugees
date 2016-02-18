'use strict';

describe('Controller Tests', function() {

    describe('Demand Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDemand, MockBranch, MockCategory, MockSeason, MockGender, MockDonationCondition, MockSize, MockStatus;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDemand = jasmine.createSpy('MockDemand');
            MockBranch = jasmine.createSpy('MockBranch');
            MockCategory = jasmine.createSpy('MockCategory');
            MockSeason = jasmine.createSpy('MockSeason');
            MockGender = jasmine.createSpy('MockGender');
            MockDonationCondition = jasmine.createSpy('MockDonationCondition');
            MockSize = jasmine.createSpy('MockSize');
            MockStatus = jasmine.createSpy('MockStatus');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Demand': MockDemand,
                'Branch': MockBranch,
                'Category': MockCategory,
                'Season': MockSeason,
                'Gender': MockGender,
                'DonationCondition': MockDonationCondition,
                'Size': MockSize,
                'Status': MockStatus
            };
            createController = function() {
                $injector.get('$controller')("DemandDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:demandUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

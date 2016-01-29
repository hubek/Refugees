'use strict';

describe('Controller Tests', function() {

    describe('Organization Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOrganization, MockOrganizationType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOrganization = jasmine.createSpy('MockOrganization');
            MockOrganizationType = jasmine.createSpy('MockOrganizationType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Organization': MockOrganization,
                'OrganizationType': MockOrganizationType
            };
            createController = function() {
                $injector.get('$controller')("OrganizationDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:organizationUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

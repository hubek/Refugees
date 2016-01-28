'use strict';

describe('Controller Tests', function() {

    describe('OrganizationType Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOrganizationType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOrganizationType = jasmine.createSpy('MockOrganizationType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OrganizationType': MockOrganizationType
            };
            createController = function() {
                $injector.get('$controller')("OrganizationTypeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:organizationTypeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

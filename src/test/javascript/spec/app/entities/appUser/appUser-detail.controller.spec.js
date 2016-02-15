'use strict';

describe('Controller Tests', function() {

    describe('AppUser Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockAppUser, MockBranch, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockAppUser = jasmine.createSpy('MockAppUser');
            MockBranch = jasmine.createSpy('MockBranch');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'AppUser': MockAppUser,
                'Branch': MockBranch,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("AppUserDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:appUserUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

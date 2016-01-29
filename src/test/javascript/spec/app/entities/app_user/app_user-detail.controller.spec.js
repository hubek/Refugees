'use strict';

describe('Controller Tests', function() {

    describe('App_user Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApp_user, MockBranch, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApp_user = jasmine.createSpy('MockApp_user');
            MockBranch = jasmine.createSpy('MockBranch');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'App_user': MockApp_user,
                'Branch': MockBranch,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("App_userDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'refugeesApp:app_userUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

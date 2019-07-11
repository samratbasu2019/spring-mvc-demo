var myApp=angular.module("myApp", ["ngRoute","myAppControllers"]);

myApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/', {
        templateUrl: 'view/logon.html',
        controller: 'logoncontroller'
    }).
    when('/registration', {
        templateUrl: 'view/registration.html',
        controller: 'registrationcontroller'
    }).
    when('/userdetails/:username', {
        templateUrl: 'view/userdetails.html',
        controller: 'userdetailscontroller'
    }).
    otherwise({
        redirectTo: '/'
    });
}]);


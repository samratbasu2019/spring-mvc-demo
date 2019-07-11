var myAppControllers = angular.module('myAppControllers', ['ngResource']);


myAppControllers.controller("logoncontroller", function($scope, $location,$http,$rootScope) 
    {
	
	$scope.direct=function()
	{
      	$location.path("/registration");
	}
	
	$scope.logon=function()
	{
		var username=$scope.username;
		var password=$scope.password;
		$scope.isvalid=false;
		var userlogon = null;
		$http.get("http://localhost:8080/springangularDemo/getUsers?username="+username+"&password="+password).then(function(response){
			userlogon=response.data;		
			if (userlogon.length > 0){	
				$rootScope.user=userlogon;
				$location.path("/userdetails/"+username);		
			}
			else
				$scope.isvalid=true;
			},function(){
				console.log("Error while executing the code");

			});

	}
	
    }
);


myAppControllers.controller("registrationcontroller",  function($scope,$resource,$location) {
       
	$scope.submit=function() {
        var dataObj = {
                username : $scope.username,
                password : $scope.password,
                address : $scope.address,
                email : $scope.email,
                state : $scope.state,
                city : $scope.city
        };  
        
        $scope.hassave=false;
        $resource('http://localhost:8080/springangularDemo/addUser').save(dataObj).$promise.then(function(){			
        	$scope.hassave=true;
		}).then(function(error){		
			//alert ("Error page" + error);
		});
       // alert(dataObj);
        
	}
});


myAppControllers.controller("userdetailscontroller", function($scope, $location,$http,$rootScope) 
	    {
		$scope.user=$rootScope.user;
		console.log($scope.user);
	    });
var app=angular.module('app',[]);
app.controller('loginController',function($scope,$http,$location,$window){
    $scope.login=function(){
        var url="login";
        var data={
email:$scope.email,
password:$scope.password
        };

        $http.post(url,data).then(function(response)
    {
    var userHeader=response.headers('User');
        var userLogin=userHeader.split(';')[0];
        var userRole=userHeader.split(';')[1];

        if(userRole=='parent')
        {
$window.location.href=$location.absoluteUrl()+"ana.html";

       }


    }, function(response){

        $scope.message="Failed!";
    });
    }
});

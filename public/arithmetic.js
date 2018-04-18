var app = angular.module('application', []);

app.controller('controller', function($scope, $http) {
    $scope.count = 0;
    $scope.myFunction = function() {
        $http.get("http://localhost:8080/add")
            .then(function(response) {
                $scope.myVar = response.data;
            });
        //$scope.myVar = "sent";
    }
});
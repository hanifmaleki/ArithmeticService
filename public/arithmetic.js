var env = {};

// Import variables if present (from env.js)
if(window){
  Object.assign(env, window.__env);
}

var app = angular.module('application', []);
app.constant('__env', env);
app.controller('controller', function($scope, $http) {
    $scope.requests = [];
    $scope.exprPattern = /^\s*\d+\s*[-\/\*\+]\s*\d+\s*$/;
    $scope.clientId = Math.floor((Math.random() * 1000) + 1);
    $scope.requestId = 1;
    $scope.myFunction = function() {
        var ex = $scope.expression + '';
        res = ex.split(/[+*-\/]/);
        var op = ex.split(/\s*\d+s*/);
        //$scope.value3 = op[1];
        //$scope.value4 = op;
        var request = {request:ex, status:"sent", answer:"NaN"};
        $http.get(__env.apiUrl,  {
                                                   params: { operator: op[1].trim(),
                                                   operand1: parseInt(res[0]),
                                                   operand2: parseInt(res[1]),
                                                   clientId: $scope.clientId,
                                                   id: $scope.requestId}
                                               })
            .then(function(response) {
                request.status="processed at server"
            });
       $scope.requestId++;
       $scope.requests.push(request);
       $scope.expression = "";
    }
});

/*app.directive('exprValid', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attr, controller) {
      function myValidation(value) {
        var re = /^\s*\d+\s*[-\/\*\+]\s*\d+\s*$/;
        scope.checked=!re.test(value);
        controller.$setValidity('expr', re.test(value));
        return re.test(value);
      }
      controller.$parsers.push(myValidation);
    }
  };
});*/


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
    $scope.myFunction = function() {
        var ex = $scope.expression + '';
        res = ex.split(/[ +*-\/]/);
        $scope.value3 = parseInt(res[0]);
        $scope.value4 = parseInt(res[1]);
        var request = {request:ex, status:"sent", answer:"NaN"};
        $http.get(__env.apiUrl,  {
                                                   params: { operator: "test",
                                                   operand1: parseInt(res[0]),
                                                   operand2: parseInt(res[1])}
                                               })
            .then(function(response) {
                request.status="processed at server"
            });
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


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
    $scope.batchSize = 5 ;
    $scope.myFunction = function() {
        var ex = $scope.expression + '';
        res = ex.split(/[+*-\/]/);
        var op = ex.split(/\s*\d+s*/);
        //$scope.value3 = op[1];
        //$scope.value4 = op;
        sendRequest($scope, $http, ex, op[0].trim(), res[0], res[1]);
       $scope.expression = "";
    }

    $scope.sendBatch = function(){
        for(var i=0; i < $scope.batchSize; i++){
            var op = "";
            var rand = Math.floor((Math.random() * 4));
            switch(rand){
                case 0:
                    op="+";
                    break;
                case 1:
                    op = "-";
                    break;
                case 2:
                    op = "*";
                    break;
                case 3:
                    op = "/";
                    break;
            }
            var n1 = Math.floor((Math.random() * 100));
            var n2 = Math.floor((Math.random() * 100));
            var ex = ""+ n1 + op + n2 ;
            sendRequest($scope, $http, ex, op, n1, n2);
        }
    }
});


function sendRequest(scope, http, ex, operator, operand1, operand2){
     var request = {request:ex, status:"sent", answer:"NaN"};
             http.get(__env.apiUrl,  {
                                                        params: { operator,
                                                        operand1: operand1,
                                                        operand2: operand2,
                                                        clientId: scope.clientId,
                                                        id: scope.requestId}
                                                    })
                 .then(function(response) {
                     request.status="processed at server"
                 });
            scope.requestId++;
            scope.requests.push(request);
}


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


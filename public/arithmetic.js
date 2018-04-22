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
    $scope.clientNumber = Math.floor((Math.random() * 1000) + 1);
    $scope.requestId = 1;
    $scope.batchSize = 5 ;
    $scope.myFunction = function() {
        var ex = $scope.expression + '';
        res = ex.split(/[+*-\/]/);
        var op = ex.split(/\s*\d+s*/);
        //$scope.value3 = op[1];
        //$scope.value4 = op;
        sendRequest($scope, $http, ex, op[1].trim(), res[0], res[1]);
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

    $scope.pullResults = function(){
      var pullRequests=[];
      var params = "?clientNumber="+$scope.clientNumber ;
      for(var i = 0; i < $scope.requests.length; i++){
        var request = $scope.requests[i];
        if(isNaN(request.answer)){
          //var pullRequest = {clientNumber: $scope.clientNumber, clientId: request.clientId}
          //pullRequests.push({id: request.clientId});
          pullRequests.push(request.clientId);
          params+="&id="+request.clientId ;
        }
      }
      $scope.value3 = pullRequests.length;
      if(pullRequests.length>0){
        //$http.get(__env.apiUrl+"/answers",  {params: {clientNumber: $scope.clientNumber, ids :JSON.stringify(pullRequests)}})
        //$http.get(__env.apiUrl+"/answers",  {params: {clientNumber: $scope.clientNumber, "ids[]" :pullRequests}})
        $http.get(__env.apiUrl+"/answers"+ params)
               .then(function(response) {
                    $scope.myVar = response.data;
               });
      }
    }
});


function sendRequest(scope, http, ex, operator, operand1, operand2){
     var request = {request:ex, status:"sent", answer:NaN, clientId: scope.requestId};
             http.get(__env.apiUrl+"/add",  {
                                                        params: { operator:operator,
                                                        operand1: operand1,
                                                        operand2: operand2,
                                                        clientNumber: scope.clientNumber,
                                                        clientId: scope.requestId}
                                                    })
                 .then(function(response) {
                     request.status="processed at server";
                     request.answer=response.data;
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


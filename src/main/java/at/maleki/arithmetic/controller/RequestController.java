package at.maleki.arithmetic.controller;

import at.maleki.arithmetic.business.PullRequestHandler;
import at.maleki.arithmetic.business.RequestProcessor;
import at.maleki.arithmetic.model.PullRequest;
import at.maleki.arithmetic.model.Request;
import at.maleki.arithmetic.model.Request.Operator;
import at.maleki.arithmetic.model.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Created by e1528895 on 4/18/18. */
@CrossOrigin
@RestController
@Slf4j
public class RequestController {

  @Autowired
  RequestProcessor requestProcessor;

  @Autowired
  PullRequestHandler pullRequestHandler;

  @RequestMapping("/add")
  Response addRequest(
      String operator, Integer operand1, Integer operand2, Integer clientNumber, Integer clientId) {
    log.debug("Request is received with  operator"+ operator+" operand1 "+operand1+" operand2 "
        +operand2+" clientNumber "+clientNumber+" clientId "+clientId);
    Request request = new Request();
    request.setClientNumber(clientNumber);
    request.setClientId(clientId);
    request.setOperand1(operand1);
    request.setOperand2(operand2);
    Request.Operator op = null;
    switch (operator) {
      case "+":
        op = Operator.Add;
        break;
      case "-":
        op = Operator.Sub;
        break;
      case "*":
        op = Operator.Mult;
        break;
      case "/":
        op = Operator.Divide;
        break;
    }
    request.setOperator(op);
    request.setReceiveDate(new Date());

    Response response = requestProcessor.processRequest(request);
    return response;
  }

  @RequestMapping("/answers")
  List<Request> getNewAnswers(Integer clientNumber,
      @RequestParam(value="id") ArrayList<Integer> clientIds){
    log.debug("Receiving a pull requests for client "+clientNumber +" and with size "+clientIds.size());
    List<PullRequest> pullRequests = new ArrayList<>();
    for(Integer id: clientIds){
      PullRequest pullRequest = new PullRequest();
      pullRequest.setClientNumber(clientNumber);
      pullRequest.setClientId(id);
      pullRequests.add(pullRequest);
    }

    List<Request> requests = pullRequestHandler.fetchAvailableAnswers(pullRequests);
    return requests ;
  }

}

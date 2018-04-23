package at.maleki.arithmetic.business;

import at.maleki.arithmetic.model.Request;
import org.springframework.stereotype.Component;

/**
 * Created by e1528895 on 4/22/18. The implementation of @{@link RequestCalculator} It simply
 * calculate the results of the requests
 */
@Component
public class RequestCalculatorImpl extends AbstractRequestCalculator {

  @Override
  public Float calculate(Request request) {
    Float answer = null;
    switch (request.getOperator()) {
      case Add:
        answer = Float.valueOf(request.getOperand1() + request.getOperand2());
        break;
      case Mult:
        answer = Float.valueOf(request.getOperand1() * request.getOperand2());
        break;
      case Sub:
        answer = Float.valueOf(request.getOperand1() - request.getOperand2());
        break;
      case Divide:
        answer = Float.valueOf(request.getOperand1()) / Float.valueOf(request.getOperand2());
        break;
    }

    return answer;
  }
}

package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.business.RequestCalculator;
import at.maleki.arithmetic.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Hanif Maleki on 4/22/18. This is an implementation of the @{@link RequestSubscriber}
 * It simply calculate the answer of the request using a @{@link RequestCalculator}
 */
@Component
public class RequestSubscriberImpl implements RequestSubscriber {
  @Autowired RequestCalculator requestCalculator;

  @Override
  public void requestReceived(Request request) {
    requestCalculator.calculateAnswer(request);
  }
}

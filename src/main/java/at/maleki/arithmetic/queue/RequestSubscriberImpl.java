package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.business.RequestCalculator;
import at.maleki.arithmetic.dao.RequestDao;
import at.maleki.arithmetic.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by e1528895 on 4/22/18.
 */
@Component
public class RequestSubscriberImpl implements RequestSubscriber{
  @Autowired
  RequestCalculator requestCalculator;

  @Override
  public void requestReceived(Request request) {
    requestCalculator.calculateAnswer(request);
  }
}

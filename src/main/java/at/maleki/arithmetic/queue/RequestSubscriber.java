package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/22/18.
 */
public interface RequestSubscriber {

  void requestReceived(Request request);

}

package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/22/18. A subscriber of the @{@link Request} should calculate the answer
 * for it.
 */
public interface RequestSubscriber {

  void requestReceived(Request request);
}

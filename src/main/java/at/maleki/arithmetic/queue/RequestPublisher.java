package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/21/18. An interface of publishing a @{@link Request} in order to make it
 * ready for answer calculation
 */
public interface RequestPublisher {
  void publish(Request request);
}

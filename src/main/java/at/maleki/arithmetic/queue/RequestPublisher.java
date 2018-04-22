package at.maleki.arithmetic.queue;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/21/18.
 */
public interface RequestPublisher {
    void publish(Request request);
}

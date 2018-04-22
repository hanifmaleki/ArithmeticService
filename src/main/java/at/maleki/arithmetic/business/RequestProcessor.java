package at.maleki.arithmetic.business;

import at.maleki.arithmetic.model.Request;
import at.maleki.arithmetic.model.Response;

/**
 * Created by e1528895 on 4/21/18.
 */
public interface RequestProcessor {

  Response processRequest(Request request);
}

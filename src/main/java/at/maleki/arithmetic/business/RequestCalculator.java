package at.maleki.arithmetic.business;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/22/18.
 * The aim of this class is to calculate the answer for a request
 */
public interface RequestCalculator {

  public void calculateAnswer(Request request);

}

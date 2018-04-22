package at.maleki.arithmetic.dao;

import at.maleki.arithmetic.model.Request;

/**
 * Created by e1528895 on 4/21/18.
 */
public interface RequestDao {

  void save(Request request);

  void update(Request request);

  Request getRequestBy(Integer clientNumber, Integer clientId);
}

package at.maleki.arithmetic.business;

import at.maleki.arithmetic.dao.RequestDao;
import at.maleki.arithmetic.model.Request;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by e1528895 on 4/22/18.
 * An abstract implementation of RequestCalculator
 * which update the obtained request in database
 */
@Slf4j
public abstract class AbstractRequestCalculator implements RequestCalculator{
  @Autowired
  RequestDao requestDao ;

  @Override
  public void calculateAnswer(Request request) {
    Float answer = calculate(request);
    log.debug("the answer calculated " + answer);
    request.setAnswer(answer);
    request.setProcessedDate(new Date());
    requestDao.update(request);
  }

  abstract Float calculate(Request request) ;
}

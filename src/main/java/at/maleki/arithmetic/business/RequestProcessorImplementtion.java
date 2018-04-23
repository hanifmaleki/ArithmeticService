package at.maleki.arithmetic.business;

import at.maleki.arithmetic.dao.RequestDao;
import at.maleki.arithmetic.model.Request;
import at.maleki.arithmetic.model.Response;
import at.maleki.arithmetic.queue.RequestPublisher;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Hanif Maleki on 4/21/18. This class is an implementation of @{@link RequestProcessor }
 * For every request a new instance of this class is created
 */
// @Component
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class RequestProcessorImplementtion implements RequestProcessor {

  @Autowired private RequestDao requestDao;

  @Autowired private RequestPublisher requestPublisher;

  @Override
  public Response processRequest(Request request) {
    Response response = new Response();
    Random rand = new Random();
    int n = rand.nextInt(20) + 1;
    response.setAnswer(n);
    String requestString =
        "Processed "
            + request.getOperand1()
            + request.getOperator()
            + request.getOperand2()
            + " "
            + n;
    response.setResponse(requestString);
    log.debug(requestString);
    System.out.println(requestString);
    try {
      Thread.sleep(n * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    requestDao.save(request);
    log.debug("request saved " + request);

    requestPublisher.publish(request);
    log.debug("request published " + request);
    return response;
  }
}

package at.maleki.arithmetic.business;

import at.maleki.arithmetic.dao.RequestDao;
import at.maleki.arithmetic.model.PullRequest;
import at.maleki.arithmetic.model.Request;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by e1528895 on 4/22/18. The implementation of @{@link PullRequestHandler} Fetch answers
 * from the database and create a list of ready answers
 */
@Component
@Slf4j
public class PullRequestHandlerImpl implements PullRequestHandler {
  @Autowired RequestDao requestDao;

  @Override
  public List<Request> fetchAvailableAnswers(List<PullRequest> pullRequests) {
    List<Request> returnedList = new ArrayList<>();
    log.debug("Processing pull requests consisting of " + pullRequests.size());
    for (PullRequest pullRequest : pullRequests) {
      Request request =
          requestDao.getRequestBy(pullRequest.getClientNumber(), pullRequest.getClientId());
      if (request.getAnswer() != null) {
        request.setReturnedDate(new Date());
        log.debug("request is found" + pullRequest + " with answer " + request.getAnswer());
        requestDao.update(request);
        returnedList.add(request);
      }
    }
    return returnedList;
  }
}

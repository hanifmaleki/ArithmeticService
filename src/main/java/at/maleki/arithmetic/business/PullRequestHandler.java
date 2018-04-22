package at.maleki.arithmetic.business;

import at.maleki.arithmetic.model.PullRequest;
import at.maleki.arithmetic.model.Request;
import java.util.List;

/**
 * Created by e1528895 on 4/22/18.
 */
public interface PullRequestHandler {

  public List<Request> fetchAvailableAnswers(List<PullRequest> pullRequests);

}

package at.maleki.arithmetic.business;

import at.maleki.arithmetic.model.PullRequest;
import at.maleki.arithmetic.model.Request;
import java.util.List;

/**
 * Created by e1528895 on 4/22/18. This interface handles the reuests of clients for providing the
 * answer of requests
 */
public interface PullRequestHandler {

  public List<Request> fetchAvailableAnswers(List<PullRequest> pullRequests);
}

package at.maleki.arithmetic.model;

import lombok.Data;

/** Created by e1528895 on 4/18/18. The first response of the server to the client */
@Data
public class Response {

  private String response;
  private Integer answer;
}

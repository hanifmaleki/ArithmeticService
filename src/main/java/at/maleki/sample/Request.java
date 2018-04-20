package at.maleki.sample;

import lombok.Data;

/**
 * Created by e1528895 on 4/18/18.
 */

@Data
public class Request {

  enum Operator {Add, Sub, Mult, Divide  }

  private Integer id;

  private Integer clientId;

  private Integer operand1;

  private Integer operand2;

  private Operator operator;

  private Integer answer;

}

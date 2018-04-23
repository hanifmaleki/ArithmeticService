package at.maleki.arithmetic.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** Created by e1528895 on 4/18/18. The model of the arithmetic requests */
@Data
public class Request implements Serializable {

  public enum Operator {
    Add,
    Sub,
    Mult,
    Divide
  }

  private Integer id;

  private Integer clientId;

  private Integer clientNumber;

  private Integer operand1;

  private Integer operand2;

  private Operator operator;

  private Float answer;

  private Date receiveDate;

  private Date processedDate;

  private Date returnedDate;
}

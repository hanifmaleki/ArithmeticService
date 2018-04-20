package at.maleki.sample;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by e1528895 on 4/18/18.
 */
@CrossOrigin
@RestController
@Slf4j
public class RequestController {
   @RequestMapping("/add")
  Response addRequest(String operator, Integer operand1, Integer operand2){

     Response response = new Response();
     Random rand = new Random();

     int  n = rand.nextInt(20) + 1;
     response.setAnswer(n);
     response.setResponse("Processed "+operand1+operator+operand2+" "+ n);
     log.debug("Processed "+operand1+operator+operand2+" "+ n);
    System.out.println("Processed "+operand1+operator+operand2+" "+ n);
     //n = rand.nextInt(20000) ;
     try {
       Thread.sleep(n*1000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     return response;

  }

}

package at.maleki.sample;

import java.util.Random;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by e1528895 on 4/18/18.
 */
@CrossOrigin
@RestController
public class RequestController {
   @RequestMapping("/add")
  Response addRequest(String operator, Integer operand1, Integer operand2){

     Response response = new Response();
     Random rand = new Random();

     int  n = rand.nextInt(20) + 1;
     response.setAnswer(n);
     response.setResponse("Processed "+operand1+operator+operand2+" "+ n);
     //n = rand.nextInt(20000) ;
     try {
       Thread.sleep(n*1000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     return response;

  }

}

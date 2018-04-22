package at.maleki.arithmetic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by e1528895 on 4/18/18.
 */
@SpringBootApplication
@Slf4j
public class ArithmaticApplication {

  public static void main(String[] args) {
    log.debug("Application is starting");
    SpringApplication.run(ArithmaticApplication.class, args);
    log.debug("Application has been started");
  }


}

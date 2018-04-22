package at.maleki.arithmetic.redis;

import at.maleki.arithmetic.model.Request;
import at.maleki.arithmetic.queue.RequestSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/** Created by e1528895 on 4/21/18. */
@Service
@Slf4j
public class RequestSubscriberConfigurator implements MessageListener {

  @Autowired
  ObjectMapper objectMapper ;

  @Autowired
  RequestSubscriber messagePubisher;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String jsonString = new String(message.getBody());
    jsonString =jsonString.substring(1,jsonString.length()-1);
    jsonString= jsonString.replace("\\", "");
    log.debug("Message received: " + jsonString);
    try {
      Request request = objectMapper.readValue(jsonString, Request.class);
      log.debug("Converted request is "+ request);
      messagePubisher.requestReceived(request);
    } catch (IOException e) {
      e.printStackTrace();
      log.error(e.getMessage());
    }

  }

}

package at.maleki.arithmetic.redis;

import at.maleki.arithmetic.model.Request;
import at.maleki.arithmetic.queue.RequestPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/** Created by e1528895 on 4/21/18. */
@Component
@Slf4j
public class RequestPublisherImpl implements RequestPublisher {

  @Autowired private RedisTemplate<String, Object> redisTemplate;
  @Autowired private ChannelTopic topic;
  @Autowired ObjectMapper objectMapper;

  @Override
  public void publish(Request request) {
    String jsonString = null;
    try {
      jsonString = objectMapper.writeValueAsString(request);
      log.debug("Pushing "+jsonString);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    redisTemplate.convertAndSend(topic.getTopic(), jsonString);
  }
}

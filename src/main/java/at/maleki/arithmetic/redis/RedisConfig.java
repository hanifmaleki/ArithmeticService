package at.maleki.arithmetic.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by e1528895 on 4/21/18. This class create Redis template and establish
 * MessageListenerContainer
 */
@Configuration
@ComponentScan // ("at.maleki.arithmetic")
public class RedisConfig {

  // @Bean
  // JedisConnectionFactory jedisConnectionFactory() {
  //  return new JedisConnectionFactory();
  // }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    // template.setConnectionFactory(jedisConnectionFactory());
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    // return redisTemplate;
    // template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }

  @Bean
  MessageListenerAdapter messageListener(
      RequestSubscriberConfigurator requestSubscriberConfigurator) {
    // return new MessageListenerAdapter(new RequestSubscriberConfigurator());
    return new MessageListenerAdapter(requestSubscriberConfigurator);
  }

  @Bean
  RedisMessageListenerContainer redisContainer(
      RedisConnectionFactory connectionFactory,
      RequestSubscriberConfigurator requestSubscriberConfigurator) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(messageListener(requestSubscriberConfigurator), requestTopic());
    return container;
  }

  /*@Bean
  RequestPublisher redisPublisher() {
    return new RequestPublisherImpl(redisTemplate(), requestTopic());
    //return new RequestPublisherImpl();
  }*/

  @Bean
  ChannelTopic requestTopic() {
    return new ChannelTopic("request:queue");
  }
}

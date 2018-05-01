package at.maleki.arithmetic.redis;

import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
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
@Slf4j
@ComponentScan // ("at.maleki.arithmetic")
public class RedisConfig {

   /*@Bean
   JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
   }*/

  @Bean
  public RedisConnectionFactory lettuceConnectionFactory() {
    /*RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
        .master("mymaster")
        .sentinel("127.0.0.1", 26379)
        .sentinel("127.0.0.1", 26380);
    return new LettuceConnectionFactory(sentinelConfig);*/
    //String redistogoUrl = System.getenv("REDISTOGO_URL");
    String redisUrl = System.getenv("REDIS_URL");
    log.debug(redisUrl);
    if(redisUrl==null)
      return new LettuceConnectionFactory();
    try {
      URI redisUri = new URI(redisUrl);
      String host = redisUri.getHost();
      int port = redisUri.getPort();
      String password = redisUri.getUserInfo().split(":", 2)[1];
      RedisPassword redisPassword = RedisPassword.of(password);
      RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
      configuration.setPassword(redisPassword);
      return new LettuceConnectionFactory(configuration);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  /*public RedisConfig(){
    try {
      String redistogoUrl = System.getenv("REDISTOGO_URL");
      URI redistogoUri = new URI(redistogoUrl);

      JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();

      jedisConnFactory.setUsePool(true);
      jedisConnFactory.setHostName(redistogoUri.getHost());
      jedisConnFactory.setPort(redistogoUri.getPort());
      jedisConnFactory.setTimeout(Protocol.DEFAULT_TIMEOUT);
      jedisConnFactory.setPassword(redistogoUri.getUserInfo().split(":", 2)[1]);

      return jedisConnFactory;

    } catch (URISyntaxException e) {
      e.printStackTrace();
      return null;
    }
  }*/

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    //template.setConnectionFactory(jedisConnectionFactory());
    log.debug(connectionFactory.getClass().toString());

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

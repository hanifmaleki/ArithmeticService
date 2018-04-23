package at.maleki.arithmetic.redis;

import at.maleki.arithmetic.dao.RequestDao;
import at.maleki.arithmetic.model.Request;
import javax.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/** Created by Hanif Naleki on 4/21/18. The Redis implementation of @{@link RequestDao} */
@Component
// @Repository
public class RequestDaoImpl implements RequestDao {

  private static final String KEY = "Request";
  private static final String KEY2 = "RequestClient";

  private static Integer serverId = 1;
  private RedisTemplate<String, Object> redisTemplate;

  @Resource(name = "redisTemplate")
  private HashOperations hashOperations;

  @Override
  public void save(Request request) {
    request.setId(serverId);
    hashOperations.put(KEY, serverId, request);

    ComposedKey composedKey = new ComposedKey();
    composedKey.clientId = request.getClientId();
    composedKey.clientNumber = request.getClientNumber();
    hashOperations.put(KEY2, composedKey.hashCode(), serverId);
    // TODO manage exception if such number has already exist
    serverId++;
  }

  @Override
  public void update(Request request) {
    if (request.getId() == null) {
      // TODO manage exception
    }
    hashOperations.delete(KEY, request.getId());
    // TODO manage exception if there does not exist such item

    hashOperations.put(KEY, request.getId(), request);
  }

  @Override
  public Request getRequestBy(Integer clientNumber, Integer clientId) {
    // TODO manage if one is null
    ComposedKey composedKey = new ComposedKey();
    composedKey.clientId = clientId;
    composedKey.clientNumber = clientNumber;
    Integer id = (Integer) hashOperations.get(KEY2, composedKey.hashCode());
    // TODO manage exception if such id does not exist
    Request request = (Request) hashOperations.get(KEY, id);
    return request;
  }

  class ComposedKey {
    Integer clientNumber;
    Integer clientId;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      ComposedKey that = (ComposedKey) o;

      if (clientNumber != null
          ? !clientNumber.equals(that.clientNumber)
          : that.clientNumber != null) {
        return false;
      }
      return clientId != null ? clientId.equals(that.clientId) : that.clientId == null;
    }

    @Override
    public int hashCode() {
      int result = clientNumber != null ? clientNumber.hashCode() : 0;
      result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
      return result;
    }
  }
}

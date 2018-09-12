package redis;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisUtils {
	private static Logger logger = Logger.getLogger(RedisUtils.class);
	private static RedisTemplate<Serializable, Object> redisTemplate;

	// 写入或者更新缓存
	public static boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			logger.error("write redis is faill");
			e.printStackTrace();
		}
		return result;
	}

	public static void setRedisTemplate(
			RedisTemplate<Serializable, Object> redisTemplate) {
		RedisUtils.redisTemplate = redisTemplate;
	}

	// 写入缓存设置失效时间
	public static boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//读取缓存
	public static Object get(final String key){
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	//判断是否有对应的value
	public static boolean exists(final String key){
		return redisTemplate.hasKey(key);
	}
	
	//删除对应的value
	public static void remove(final String key){
		if(exists(key)){
			redisTemplate.delete(key);
		}
	}
	
	//删除批量的value
	public static void remove(final String... keys){
		for(String key : keys){
			remove(key);
		}
	}
	
	//批量删除key
	public static void removePattern(final String pattern){
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if(keys.size()>0){
			redisTemplate.delete(keys);
		}
	}
	
}

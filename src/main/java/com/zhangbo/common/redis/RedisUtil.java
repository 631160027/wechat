package com.zhangbo.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     * 
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * 
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     * 
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * 
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * 
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 写入缓存
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean setNX(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.setIfAbsent(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @SuppressWarnings({"rawtypes"})
	public List<String> getAll(final String keya) {
    	List<String> list = new ArrayList<>();
    	Set set = redisTemplate.keys(keya);
    	Iterator it = set.iterator();
    	while (it.hasNext()) {
    		String key = (String) it.next();
    		list.add(key);
    	}
    	return list;
    }

    public void batchDel(String pre_str){
        Set<String> set = redisTemplate.keys(pre_str +"*");
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String keyStr = it.next();
            redisTemplate.delete(keyStr);
        }
    }
    
	public Long increment(final String key, final long delta) {
		Long result = null;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			result = operations.increment(key, delta);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	public boolean expireTime(final String key, Long expireTime){
		boolean bool = false;
		try {
			Boolean b = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			if (null != b && b.booleanValue()){
				bool = b.booleanValue();
			}else{
				bool = false;
			}
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
    
	/**
     * 读取缓存TTL
     * getExpire 有BUG！
     *
     * @param redisKey 缓存Key
     * @return TTL
     */
    public Long getTTL(String redisKey) {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = factory.getConnection();
        try {
            return redisConnection.ttl(redisKey.getBytes());
        } finally {
            redisConnection.close();
        }
    }
}

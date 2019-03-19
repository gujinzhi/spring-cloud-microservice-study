package com.daqsoft.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value,Long timeout) {
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key,value,timeout, TimeUnit.SECONDS);
//        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
//        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
//        stringObjectObjectHashOperations.put(key,key,value);/**/
//        vo.set(key, value,timeout);
    }
    public void set(String key, Object value) {
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}

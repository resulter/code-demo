package com.example.codedemo.dao.impl;

import com.example.codedemo.dao.RedisDao;
import com.example.codedemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

@Component
public class RedisDaoImpl implements RedisDao {
    @Autowired
    RedisService redisService;

    @Override
    public synchronized String get(String key) {

        Jedis jedis=null;
        String result=null;
        try {
            jedis=redisService.getInstance();
            result = jedis.get(key);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis=null;
        String result=null;
        long re = 0L;
        try {
            jedis=redisService.getInstance();
            Transaction tx = jedis.multi();
            Response<Long> incr = tx.incr(key);
            tx.exec();
            re=incr.get().longValue();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return re;
    }
}

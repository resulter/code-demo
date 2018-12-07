package com.example.codedemo.dao;

import org.springframework.stereotype.Component;

@Component
public interface RedisDao {
    String get(String key);

    /**
     * 自增,+1,返回增加后的值
     *
     * @param key
     * @return
     */
    long incr(String key);
}
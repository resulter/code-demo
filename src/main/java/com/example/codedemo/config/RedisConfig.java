package com.example.codedemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Configuration
public class RedisConfig {
    /**
     * 日志管理
     */
    private Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${efang.host}")
    private String host;

    @Value("${efang.port}")
    private int port;

    /**
     *
     * @Title: getJedisPool
     * @Description: 获取jedisPool
     * @return
     */
    @Bean
    public JedisPool getJedisPool() {
        log.info("==>初始化jedis连接池");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(51);
        JedisPool pool = new JedisPool(config, host, port);
        return pool;
    }
}

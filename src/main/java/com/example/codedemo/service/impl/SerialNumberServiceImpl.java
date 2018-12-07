package com.example.codedemo.service.impl;

import com.example.codedemo.utils.StringUtil;
import com.example.codedemo.utils.TimeUtil;
import com.example.codedemo.dao.RedisDao;
import com.example.codedemo.service.SerialNumberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SerialNumberServiceImpl implements SerialNumberService {
    @Resource
    private RedisDao redisDao;

    @Override
    public String generate(String bizCode) throws Exception {
        /** 检查业务码 */
        boolean isLegal = isLegal(bizCode);
        if (!isLegal) {
            throw new Exception("bizCode参数不合法");
        }
        /** 获取今天的日期:yyyyMMdd */
        String date = TimeUtil.getNow();
        /** 构造redis的key */
        String key = SERIAL_NUMBER + date;
        /** 自增 */
        long sequence = redisDao.incr(key);
        String seq = StringUtil.getSequence(sequence);
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(bizCode).append(seq);
        String serial = sb.toString();
        return serial;
    }
}
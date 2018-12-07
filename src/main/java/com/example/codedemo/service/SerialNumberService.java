package com.example.codedemo.service;

public interface SerialNumberService {
    /**
     * 序列号自增序列
     */
    String SERIAL_NUMBER = "serial.number:";

    /**
     * 根据两位业务码字符串,生成一个流水号,格式按照:<br/>
     * yyyyMMdd{bizCode}{10位的自增序列号}
     *
     * @param bizCode
     *            两位,00-99
     * @return 20位的序列号
     * @throws Exception
     */
    String generate(String bizCode) throws Exception;

    //其实，应该对bizCode做白名单验证,以免恶意伪造
    default boolean isLegal(String bizCode) {
        if (bizCode == null || bizCode.length() != 2) {
            throw new RuntimeException("bizCode: " + bizCode + "异常");
        }
        if (Character.isDigit(bizCode.charAt(0))
                && Character.isDigit(bizCode.charAt(1)))
            return true;
        return false;
    }
}
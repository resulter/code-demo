package com.example.codedemo.utils;

public class StringUtil {
    private StringUtil() {

    }

    static final int DEFAULT_LENGTH = 10;

    /**
     * 得到10位的序列号,长度不足10位,前面补0
     *
     * @param seq
     * @return
     */
    public static String getSequence(long seq) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= DEFAULT_LENGTH) {// 取决于业务规模,应该不会到达10
            return str;
        }
        int rest = DEFAULT_LENGTH - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

}

package com.cs_liudi.oa.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String md5Digist(String source){
        String result = DigestUtils.md5Hex(source);
        return result;
    }
    public static String md5Digist(String source,Integer salt){
        char[] ca = source.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            ca[i] += salt;
        }
        String saltStr = new String(ca);
        String result = DigestUtils.md5Hex(saltStr);
        return result;
    }
}

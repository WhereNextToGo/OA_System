package com.cs_liudi.oa.utils;

import org.junit.Test;


public class MybatisUtilsTestor {

    @Test
    public void testExcuteQurey() {
        String s1 = (String) MybatisUtils.excuteQurey(sqlSession -> {
            String s = sqlSession.selectOne("test.sample");
            return s;
        });
        System.out.println(s1);
    }
    @Test
    public void testExcuteQurey1() {
            String str = (String) MybatisUtils.excuteQurey(sqlSession -> sqlSession.selectOne("test.sample"));
            System.out.println(str);
    }
}
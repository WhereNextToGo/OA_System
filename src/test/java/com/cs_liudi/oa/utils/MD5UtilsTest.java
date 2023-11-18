package com.cs_liudi.oa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilsTest {

    @Test
    public void md5Digist() {
        System.out.println(MD5Utils.md5Digist("test"));
        System.out.println(MD5Utils.md5Digist("tast"));
        System.out.println(MD5Utils.md5Digist("tsst"));
        System.out.println(MD5Utils.md5Digist("trst"));
    }
    @Test
    public void md5Digist1() {
        System.out.println(MD5Utils.md5Digist("test",188));
        System.out.println(MD5Utils.md5Digist("test",189));
        System.out.println(MD5Utils.md5Digist("test",190));
        System.out.println(MD5Utils.md5Digist("test",191));
        System.out.println(MD5Utils.md5Digist("test",192));
        System.out.println(MD5Utils.md5Digist("test",193));
        System.out.println(MD5Utils.md5Digist("test",194));
        System.out.println(MD5Utils.md5Digist("test",195));
        System.out.println(MD5Utils.md5Digist("test",196));
        System.out.println(MD5Utils.md5Digist("test",197));
    }
}
package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.Notice;
import com.cs_liudi.oa.utils.MybatisUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class NoticeDaoTest {

    @Test
    public void testInsert() {
        MybatisUtils.excuteInsert(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setReceiverId(2l);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            Integer num = dao.insert(notice);
            System.out.println(num);
            System.out.println(notice.getNoticeId());
            return  null;
        });
    }
}
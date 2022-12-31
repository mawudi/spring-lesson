package org.example;

import com.xmg.mybatis.mapper.UserMapper;
import com.xmg.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class MybatisTests extends AppTest{
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException{
        // 1、把xml文件转换为流对象
        Reader reader = Resources.getResourceAsReader("sqlMapConfig.xml");
        // 2、通过流构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 3、获取建立与Mybatis交互对象SqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("==============init================");
    }

    @Test
    public void testQueryUserById() throws Exception{
        // 肯定帮我们提供CRUD操作
        System.out.println(sqlSession);
        // 基于mapper的动态代理的调用方式
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(2L);
        System.out.println(user.getUserName() + "," + user.getUserGender() + "," + user.getUserBirthday());
    }



    @After
    public void close() {
        System.out.println("============close============");
        sqlSession.close();
    }
}

package com.qf.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * MyBatis工具类，用于对连接对象的获得、释放、提交事务、回滚事务
 */
public class MyBatisUtils {

    //构建SqlSession工厂
    private static SqlSessionFactory factory;

    //创建ThreadLocal绑定当前线程中的SqlSession对象
    private static final ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    static {
        try {
            //1.获取读取mybatis配置文件的流对象
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //2.获取SqlSession连接对象的工厂
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接（从tl中获得当前线程的SqlSession）
    public static SqlSession openSession(){
        SqlSession session = tl.get();//从当前线程获取SqlSession对象
        if(session == null){//如果没有，则从工厂中获得
            //3。通过工厂获得连接对象
            session = factory.openSession();
            tl.set(session);//将SqlSession对象存入当前线程
        }
        return session;
    }

    //释放连接（释放当前线程中的SqlSession）
    public static void closeSession(){
        SqlSession session = tl.get();
        session.close();
        tl.remove();
    }

    //提交事务（提交当前线程中的SqlSession所管理的事务）
    public static void commit(){
        SqlSession session = openSession();
        session.commit();
        closeSession();
    }

    //回滚事务（回滚当前线程中的SqlSession所管理的事务）
    public static void rollback(){
        SqlSession session = openSession();
        session.rollback();
        closeSession();
    }

    //获得接口实现类对象
    public static <T extends Object> T getMapper(Class<T> clazz){
        SqlSession session = openSession();
        return session.getMapper(clazz);//4.通过连接对象获得接口类实现对象
    }
}

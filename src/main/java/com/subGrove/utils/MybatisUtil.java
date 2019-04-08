package com.subGrove.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

    private SqlSession session;

    public MybatisUtil() throws IOException {
        // ��ȡMybatis�������ļ�,ͬʱ��Ҳ��ȥ��ȡ������mapper�ļ�
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // �õ��Ựsession����
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        // �õ��Ự
        this.session = factory.openSession();
    }

    public <T> T getMapper(Class<T> var1) {
        return session.getMapper(var1);
    }

    public SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

    public void close() {
        this.session.close();
    }

    public void rollback() {
        this.session.rollback();
    }

    public void commit() {
        this.session.commit();
    }
}

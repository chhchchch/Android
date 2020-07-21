package com.example.logintest.ServiceClass;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class BasicDao<T>{
    /**
     * 功能：通用的增删改方法，针对于任何表
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    public int update(String sql,Object...param){
        Connection connection = null;
        try{
            //1.获取连接
            connection = JDBCUtilsByDruid.getConnection();

            //2.执行增删改
            QueryRunner qr = new QueryRunner();
            int update = qr.update(connection,sql,param);
            return update;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }

    }

    /**
     * 功能：返回一个对象，针对任何表
     * @param sql
     * @param clazz
     * @param params
     * @return
     * @throws Exception
     */
    public T querySingle(String sql,Class<T> clazz,Object...params){

        Connection connection = null;
        try{
            //1.获取连接
            connection = JDBCUtilsByDruid.getConnection();

            //2.执行操作
            QueryRunner qr = new QueryRunner();

            return qr.query(connection,sql,new BeanHandler<T>(clazz),params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }

    }

    /**
     * 功能：返回多个对象，针对任何表
     * @param sql
     * @param clazz
     * @param params
     * @return
     * @throws Exception
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object...params){

        Connection connection = null;
        try{
            //1.获取连接
            connection = JDBCUtilsByDruid.getConnection();

            //2.执行操作
            QueryRunner qr = new QueryRunner();

            return qr.query(connection,sql,new BeanListHandler<T>(clazz),params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    /**
     * 功能：返回单个值
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */

    public Object scalar(String sql,Object...params){

        Connection connection = null;
        try{
            //1.获取连接
            connection = JDBCUtilsByDruid.getConnection();

            //2.执行操作
            QueryRunner qr = new QueryRunner();

            return qr.query(connection,sql,new ScalarHandler<>(),params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

}

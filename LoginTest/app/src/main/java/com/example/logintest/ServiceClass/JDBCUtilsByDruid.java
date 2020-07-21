package com.example.logintest.ServiceClass;

import android.util.Log;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

/**
 *
 * @author 14222
 */
public class JDBCUtilsByDruid {
    static DataSource ds = null;
    static {
        try{
            Log.d("chh","静态代码块执行");
            Properties info = new Properties();
            InputStream is = JDBCUtilsByDruid.class.getResourceAsStream("/assets/druid.properties");
            info.load(is);
            Log.d("chh","加载druid完成");
            //1.创建一个指定参数的数据库连接池。
            ds = DruidDataSourceFactory.createDataSource(info);
            Log.d("chh","静态代码块执行完成");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     * 功能：获取可用的连接对象
     * @return 14222
     * @throws Exception
     */
    public static Connection getConnection()throws Exception{

        Log.d("chh","获取连接执行");
        //2.从数据库连接池获取连接
        try {

            return ds.getConnection();
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    /**
     * 功能：关闭数据库的连接
     * @param set
     * @param statement
     * @param connection
     * @throws Exception
     */
    public static void close (ResultSet set, Statement statement, Connection connection){

        try {
            if(set != null){
                set.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws Exception{
//        Connection connection = JDBCUtilsByDruid.getConnection();
//    }
}

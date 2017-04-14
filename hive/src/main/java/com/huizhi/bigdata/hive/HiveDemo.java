package com.huizhi.bigdata.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public class HiveDemo {
        public static final String DBDRIVER ="org.apache.hive.jdbc.HiveDriver";
        public static final String DBURL ="jdbc:hive2://192.168.28.130:10000/default";
        public static void main(String[] args) throws Exception {
            Class.forName(DBDRIVER);
            Connection conn = DriverManager.getConnection(DBURL,"","") ;
            String sql = "SELECT pid,name,age FROM person" ;
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery() ;
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
            conn.close();
        }
    }
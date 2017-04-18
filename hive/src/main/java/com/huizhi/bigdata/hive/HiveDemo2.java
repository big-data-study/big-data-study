package com.huizhi.bigdata.hive;

import java.sql.*;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public class HiveDemo2 {
    public static final String DBDRIVER ="org.apache.hive.jdbc.HiveDriver";
    public static final String DBURL ="jdbc:hive2://192.168.28.130:10000/default";
    private static String sql = "";
    private static ResultSet res;
    private static final Logger log = Logger.getLogger(HiveDemo2.class);
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
    }

}

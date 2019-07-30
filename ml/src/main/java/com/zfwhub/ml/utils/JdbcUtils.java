package com.zfwhub.ml.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public final class JdbcUtils {
    
    // private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
    private static String url = "jdbc:oracle:thin:@localhost:49161:xe";
    private static String username = "ml";
    private static String password = "123456";
    private static DataSource dataSource;
    
    private JdbcUtils() {}
    
    static {
        dataSource = new SingleConnectionDataSource(url, username, password, false);
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
    
    public static String page(String sql, int offset, int max) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from (select row_.*, rownum rownum_ from (");
        sb.append(sql);
        sb.append(") row_ where rownum <= ");
        sb.append(offset+max);
        sb.append(") where rownum_ > ");
        sb.append(offset);
        return sb.toString();
    }

}

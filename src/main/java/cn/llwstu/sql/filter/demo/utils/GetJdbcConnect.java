package cn.llwstu.sql.filter.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: Alickx
 * @Date: 2022/06/23/19:00
 * @Description: 获取JDBC连接工具类
 */
public class GetJdbcConnect {

    private static String driver = "com.mysql.cj.jdbc.Driver";

    private static String url = "jdbc:mysql://localhost:3306/sql_filter?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";

    private static String username = "root";

    private static String password = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}

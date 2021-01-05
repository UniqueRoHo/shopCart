package projectshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DButil {
	private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/project9?serverTimezone=GMT&useUnicode=true&characterEncoding=utf8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD= "123456";
    public static Connection getConnection() {
        Connection conn = null;
        String DSNAME = "java:comp/env/jdbc/mysql";
       try {
           Context ctx = new InitialContext();
           DataSource ds = (DataSource)ctx.lookup(DSNAME);
           conn = ds.getConnection();
       } catch (NamingException | SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        return conn;
    }
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn ) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

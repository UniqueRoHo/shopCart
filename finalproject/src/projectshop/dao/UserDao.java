package projectshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectshop.model.User;
import projectshop.util.DButil;

public class UserDao {
	 private Connection conn = null;
	    private PreparedStatement stmt = null;
	    private ResultSet rs = null;
	    public User select(String ID) {
	        User user = null;
	        String sql = "SELECT userid,password,role FROM user WHERE userid = "  + ID;
	        try {
	            conn = DButil.getConnection();
	            stmt =conn.prepareStatement(sql);
	            rs = stmt.executeQuery();
	            if(rs.next()) {
	                user = new User();
	                user.setID(rs.getInt(1));
	                user.setPassword(rs.getString(2));
	                user.setRole(rs.getString(3));
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            DButil.close(rs, stmt, conn);
	        }
	        return user;
	    }
	    public boolean insert(User user) {
	        boolean flag = false;
	           String sql = "INSERT INTO user (userid,password,role,email) VALUES (?,?,?,?)";
	           
               try {
                   conn = DButil.getConnection();
                   stmt =conn.prepareStatement(sql);
                   stmt.setInt(1, user.getID());
                   stmt.setString(2, user.getPassword());
                   stmt.setString(3, user.getRole());
                   stmt.setString(4, user.getEmail());
                   if (stmt.executeUpdate() > 0) {
                       flag = true;
                   }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                DButil.close(rs, stmt, conn);
            }
               return flag;
	    }
	    public boolean find(int userid) {
	        boolean flag = false;
            String sql = "SELECT userid FROM user WHERE userid = " + userid;
            
            try {
                conn = DButil.getConnection();
                stmt =conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    flag = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                DButil.close(rs, stmt, conn);
            }
            return flag;
	    }

}

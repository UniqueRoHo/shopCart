package projectshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projectshop.model.Cart;
import projectshop.model.Product;
import projectshop.util.DButil;

public class CartDao {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    public Cart list(int userid) {
        conn = DButil.getConnection();
        Cart cart = new Cart(userid);
        String sql = "SELECT scart.productid, pic, product.name, price, scart.total FROM scart, product where scart.productid = product.productid AND userid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Product pro = new Product();
                pro.setID(rs.getInt(1));
                pro.setPic(rs.getString(2));
                pro.setName(rs.getString(3));
                pro.setPrice(rs.getFloat(4));
                pro.setTotal(rs.getInt(5));
                cart.addPro(pro);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(rs, stmt, conn);
        }
        return cart;
    }
    public boolean add(int userid, int productid, int newTotal) {
        boolean flag = false;
        String sql = "INSERT INTO scart(total, userid, productid) VALUES (?, ?, ?)";
        int oldTotal = this.select(userid, productid);
        conn = DButil.getConnection();
        if (oldTotal != 0) {
            newTotal = newTotal + oldTotal;
            sql = "UPDATE scart SET total = ? WHERE userid = ? AND productid = ?";
        }
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newTotal);
            stmt.setInt(2, userid);
            stmt.setInt(3, productid);
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
    public int select(int userid, int productid) {
        conn = DButil.getConnection();
        int total = 0;
        String sql = "SELECT total FROM scart WHERE userid = ? AND productid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userid);
            stmt.setInt(2, productid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(rs, stmt, conn);
        }
        return total;
    }
    public boolean update(int userid, int productid, int total) {
        boolean flag = false;
        conn = DButil.getConnection();
        String sql = "UPDATE scart SET total = ? WHERE userid = ? AND productid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, total);
            stmt.setInt(2, userid);
            stmt.setInt(3, productid);
            if (stmt.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    public boolean delete(int userid, int productid) {
        boolean flag = false;
        conn = DButil.getConnection();
        String sql = "DELETE FROM scart WHERE userid = ? AND productid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userid);
            stmt.setInt(2, productid);
            if (stmt.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
}

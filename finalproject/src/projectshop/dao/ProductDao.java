package projectshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projectshop.model.Pagination;
import projectshop.model.Product;
import projectshop.util.DButil;

public class ProductDao {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	public List<Product> list(Pagination pagination) {
		List<Product> list = new ArrayList<Product>();
	    
	    String sql = "SELECT productid,pic,name,price,total,click FROM product LIMIT " + pagination.getOffset() + "," + pagination.getPageSize();
	    try {
			pagination.setTotalCount(this.getProCount());
            if (pagination.getCurrentPage() > pagination.getPageCount()) {
                pagination.setCurrentPage(pagination.getPageCount());
            }
            conn = DButil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Product pro = new Product();
				pro.setID(rs.getInt(1));
				pro.setPic(rs.getString(2));
				pro.setName(rs.getString(3));
				pro.setPrice(rs.getFloat(4));
				pro.setTotal(rs.getInt(5));
				pro.setClick(rs.getInt(6));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButil.close(rs, stmt, conn);
		}
		return list;
	}
	public int getProCount() {
	    int count = 0;
	    conn = DButil.getConnection();
        String sql = "SELECT COUNT(productid) FROM product";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(rs, stmt, conn);
        }
        return count;

	}
	public boolean add(Product pro) {
		boolean flag = false;
		conn = DButil.getConnection();
		String sql = "INSERT INTO product(pic,name,price,total) VALUES (?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pro.getPic());
			stmt.setString(2, pro.getName());
			stmt.setFloat(3, pro.getPrice());
			stmt.setInt(4, pro.getTotal());
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
	public boolean delete(Product pro) {
		boolean flag = false;
		conn = DButil.getConnection();
		String sql = "DELETE FROM product WHERE productid = " + pro.getID();
		try {
			stmt = conn.prepareStatement(sql);
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
	public boolean update(Product pro) {
		boolean flag = false;
		conn = DButil.getConnection();
		String sql = "UPDATE product SET pic = ?, name = ?, price = ?, total = ? WHERE productid = " + pro.getID();
		try {
			stmt = conn.prepareStatement(sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pro.getPic());
			stmt.setString(2, pro.getName());
			stmt.setFloat(3, pro.getPrice());
			stmt.setInt(4, pro.getTotal());
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
    public Product select(Product pro) {
        String sql = "SELECT productid, pic, name, price, total FROM product WHERE productid = " + pro.getID();
        Product p = new Product();
        try {
            conn = DButil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()) {
                p.setID(rs.getInt(1));
                p.setPic(rs.getString(2));
                p.setName(rs.getString(3));
                p.setPrice(rs.getFloat(4));
                p.setTotal(rs.getInt(5));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(rs, stmt, conn);
        }
        return p;
    }
    public boolean click(int productid) {
        boolean flag = false;
        String sql = "UPDATE product SET click = click + 1 WHERE productid = ?";
        try {
            conn = DButil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productid);
            if(stmt.executeUpdate() > 0) {
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

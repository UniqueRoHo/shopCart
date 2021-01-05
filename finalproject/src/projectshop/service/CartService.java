package projectshop.service;

import projectshop.dao.CartDao;
import projectshop.model.Cart;

public class CartService {
    private CartDao dao = new CartDao();
    public boolean addPro(int userid, int productid, int newTotal) {
        boolean flag = false;
        flag = this.dao.add(userid, productid, newTotal);
        return flag;
    }
    public Cart listCart(int userid) {
        Cart cart = this.dao.list(userid);
        return cart;
    }
    public boolean updateCart(int userid, int productid, int total) {
        boolean flag = false ;
        flag = this.dao.update(userid, productid, total);
        return flag;
    }
    public boolean deleteCart(int userid, int productid) {
        boolean flag = false ;
        flag = this.dao.delete(userid, productid);
        return flag;
    }
}

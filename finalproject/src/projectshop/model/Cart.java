package projectshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart { 
    private int userid;
    private List<Product> list;
    public Cart(int userid) {
        this.userid = userid;
        this.list = new ArrayList<Product>();
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public List<Product> getList() {
        return list;
    }
    public void setList(List<Product> list) {
        this.list = list;
    }
    public void addPro(Product pro) {
        this.list.add(pro);
    }
}

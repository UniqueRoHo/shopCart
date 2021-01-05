package projectshop.service;

import java.util.ArrayList;
import java.util.List;

import projectshop.dao.ProductDao;
import projectshop.model.Pagination;
import projectshop.model.Product;

public class ProductService {
    private ProductDao dao = new ProductDao();
    public List<Product> listPro(Pagination pagination){
        List<Product> list = new ArrayList<Product>();
        list = this.dao.list(pagination);
        return list;
    }
    public int getProCount() {
        int count = 0;
        count = this.dao.getProCount();
        return count;
    }
    public boolean deletePro(Product pro) {
        boolean flag = false;
        flag = this.dao.delete(pro);
        return flag;
    }
    public boolean addPro(Product pro) {
        boolean flag = false;
        flag = this.dao.add(pro);
        return flag;
    }
    public boolean updatePro(Product pro) {
        boolean flag = false;
        flag = this.dao.update(pro);
        return flag;
    }
    public Product selectPro(Product pro) {
        Product p = null;
        p = this.dao.select(pro);
        return p;
    }
    public boolean click(int productid) {
        boolean flag = false;
        flag = this.dao.click(productid);
        return flag;
    }
}

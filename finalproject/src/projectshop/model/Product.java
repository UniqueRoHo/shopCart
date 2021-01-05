package projectshop.model;

public class Product {
    private int ID;
    private String pic;
    private String name;
    private float price;
    private int total;
    private int click;
    
    public int getClick() {
        return click;
    }
    public void setClick(int click) {
        this.click = click;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

}

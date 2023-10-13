package api;

public class BookingPojo {

    private String orderName;

    private Integer price;

    private Integer count;

    public BookingPojo() {
    }

    public BookingPojo(String orderName, Integer price, Integer count) {
        this.orderName = orderName;
        this.price = price;
        this.count = count;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

package com.example.demo.entity;
import lombok.Data;
@Data
public class order {
    private int order_id;
    private int user_id;
    private int product_id ;
    private String order_date;
    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user_id'" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", order_date'" + order_date + '\'' +
                '}'+"\r\n";
    }
}

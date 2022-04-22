package com.example.demo.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class product implements Serializable {
    private int product_id;
    private int storage;
    private int price ;
    private int sales_volume;
    private String product_type;
    private List<comment> comments;
    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", storage'" + storage + '\'' +
                ", price='" + price + '\'' +
                ", sales_volume'" + sales_volume + '\'' +
                ", product_type'" + product_type + '\'' +
                ", comment:" + comments  + '\'' +
                '}'+"\r\n";
    }

    public String tostring(int num) {
        return "Product{" +
                "product_id=" + product_id +
                ", storage'" + storage + '\'' +
                ", price='" + price + '\'' +
                ", sales_volume'" + sales_volume + '\'' +
                ", product_type'" + product_type + '\'' +
                '}';
    }
}

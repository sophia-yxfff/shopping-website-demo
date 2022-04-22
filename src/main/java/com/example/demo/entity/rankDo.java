package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class rankDo implements Serializable {
    private Long rank;
    private double sales_volume;
    private int product_id;

    @Override
    public String toString() {
        return "Rank{" +
                "rank =" + rank +
                ", product_id='" + product_id + '\'' +
                ", sales_volume'" + sales_volume + '\'' +
                '}'+"\r\n";
    }
}

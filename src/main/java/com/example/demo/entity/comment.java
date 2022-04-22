package com.example.demo.entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class comment implements Serializable {
    private int product_id;
    private int user_id;
    private String user_name;
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "product_id=" + product_id +
                "user_id=" + user_id +
                " , user_name='" + user_name + '\'' +
                ", comment'" + comment + '\'' +
                '}'+"\r\n";
    }
}

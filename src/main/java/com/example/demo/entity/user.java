package com.example.demo.entity;
import lombok.Data;
@Data
public class user {
    private int user_id;
    private String user_name;
    private int password ;
    private String email;
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}'+"\r\n";
    }
}

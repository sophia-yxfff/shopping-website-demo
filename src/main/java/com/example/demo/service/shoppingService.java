package com.example.demo.service;
import com.example.demo.entity.*;

import java.util.List;

public interface shoppingService {
    public product findProductById(int user_id,String user_name, int product_id); /**comment also appear, how??**/
    public List<comment>findCommentById(int product_id);
    public boolean updateProductById(int user_id,String user_name,int product_id);
    public void insertOrder(order order_);
    public void commentProductById(int user_id,int product_id,String user_name,String comment);
    public List<order> showOrderByType (String product_type);
    public List<rankDo> getRanking();
    public List<product>  productList();
}

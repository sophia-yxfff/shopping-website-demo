package com.example.demo.dao;
import com.example.demo.entity.comment;
import com.example.demo.entity.order;
import com.example.demo.entity.product;
import com.example.demo.entity.user;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface shoppingDao {
    public product findProductById(int product_id);
    public boolean updateProductById(int product_id);
    public void insertOrder(order order);
    public void insertComment(comment Comment);
    public List<order> showOrderByType (String product_type);
    public List<comment> findCommentById (int product_id);
    public List<product> allProduct();

}

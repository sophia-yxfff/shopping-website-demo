package com.example.demo.controller;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.*;
import com.example.demo.service.shoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

@RestController
public class userController {
     @Autowired
     shoppingService myserv;

     @RequestMapping("/{user_id}/{username}/getProduct/{product_id}")
     @ResponseBody
     public product getProduct(@PathVariable(value = "user_id") Integer user_id,@PathVariable(value = "username")
             String username,@PathVariable(value = "product_id") Integer product_id){
         product myPro = myserv.findProductById(user_id,username,product_id);
         myPro.setComments(myserv.findCommentById(product_id));
         return myPro;
     }

     @PostMapping("/{user_id}/{username}/buyProduct/{product_id}")
     @ResponseBody
     public String buyProduct(@PathVariable(value = "user_id") Integer user_id,@PathVariable(value = "username")
             String username,@PathVariable(value = "product_id") Integer product_id){
         if(myserv.updateProductById(user_id,username,product_id)==false)
             return "Out of storage!";
         order order_ = new order();
         Date date = Calendar.getInstance().getTime();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
         String order_date = dateFormat.format(date);
         order_.setUser_id(user_id);
         order_.setProduct_id(product_id);
         order_.setOrder_date(order_date);
         myserv.insertOrder(order_);
         return "Successfully bought the product at "+order_date;
     }

    @RequestMapping(value="showOrderByType")
    @ResponseBody
    public String showOrderByType(HttpServletRequest request){
        String product_type = request.getParameter("product_type");
        List<order> myOrd= myserv.showOrderByType(product_type);
        return  "Total = "+myOrd.size()+"\r\n"+ myOrd.toString()+"\r\n";
    }
    @PostMapping(value="/{user_id}/{user_name}/commentProduct/{product_id}/{comment}")
    @ResponseBody
    public String commentProduct (@PathVariable(value = "user_id") Integer user_id,@PathVariable(value = "user_name")
    String username,@PathVariable(value = "product_id") Integer product_id,
                                   @PathVariable(value = "comment") String comment_){
        myserv.commentProductById(product_id,user_id,username,comment_);
        return "Comment posted! Your comment is: "+comment_;
    }
    @RequestMapping(value="getRank")
    @ResponseBody
    public List<rankDo> getRank(){
         return myserv.getRanking();
    }
    @RequestMapping(value="productList")
    @ResponseBody
    public List<String> productList(){
         List<product> mypro = myserv.productList();
        List<String> proString = new ArrayList<String>();

        for(product x : mypro){
            proString.add(x.tostring(1));
        }
        return proString;
    }

}

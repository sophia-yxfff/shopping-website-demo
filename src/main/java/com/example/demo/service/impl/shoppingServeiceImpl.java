package com.example.demo.service.impl;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.dao.shoppingDao;
import com.example.demo.service.shoppingService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@Service
public class shoppingServeiceImpl implements shoppingService{
    @Autowired
    shoppingDao mydao;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public product findProductById(int user_id,String user_name,int product_id){
        return mydao.findProductById(product_id);
    }
    @Override
    public List<comment> findCommentById(int product_id){
        List<comment> myList = redisTemplate.opsForList().range(product_id,0,-1);
        if(myList.isEmpty()==false)
            return myList;
        else
            return mydao.findCommentById(product_id);
    }


    @Override
    public boolean updateProductById(int user_id,String user_name,int product_id){
        product mypro = mydao.findProductById(product_id);
        if(mypro.getStorage()<=0)
            return false;
        mydao.updateProductById(product_id);
        int score = mypro.getSales_volume();
        redisTemplate.opsForZSet().add("Rank", product_id, -score);
        return true;
    }
    @Override
    public void insertOrder(order order_){
        mydao.insertOrder(order_);
    }

    @Override
    public void commentProductById(int product_id, int user_id,String user_name,String comment){
        comment myComment = new comment();
        myComment.setComment(comment);
        myComment.setUser_id(user_id);
        myComment.setUser_name(user_name);
        myComment.setProduct_id(product_id);
        redisTemplate.opsForList().leftPush(product_id,myComment);
        mydao.insertComment(myComment);
    }
    @Override
    public List<order> showOrderByType (String product_type){
       return mydao.showOrderByType(product_type);
    }

    @Override
    public List<rankDo> getRanking(){
        Set<ZSetOperations.TypedTuple<Integer>> tupleSet = redisTemplate
                .opsForZSet().rangeWithScores("Rank", 0, -1);
        List<rankDo> rankList = new ArrayList<>(tupleSet.size());
        long rank = 1;
        for (ZSetOperations.TypedTuple<Integer> sub : tupleSet) {
            rankList.add(new rankDo(rank++, Math.abs(sub.getScore())+1, sub.getValue()));
        }
        return rankList;
    }

    @Override
    public List<product> productList(){
        return mydao.allProduct();
    }



}

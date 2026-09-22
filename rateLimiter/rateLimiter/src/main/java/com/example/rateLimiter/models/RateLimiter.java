package com.example.rateLimiter.models;

import java.util.ArrayList;


public class RateLimiter{
   public int capacity;
   public  ArrayList<Request>requests=new ArrayList<>();
    public RateLimiterStrategy strategy;
    public RateLimiter(int capacity,String strategy){
        this.capacity=capacity;
        this.strategy= new RateLimiterStrategyfactory(strategy).build();
        
    }
    public void addRequests(Request req) {
        requests.add(req);
       
    }
    public String limitRequest(){
        return strategy.limitRequest(requests,capacity);
    }

}
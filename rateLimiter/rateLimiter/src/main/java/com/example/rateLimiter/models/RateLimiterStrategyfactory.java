package com.example.rateLimiter.models;

public  class RateLimiterStrategyfactory{
    public RateLimiterStrategy rateStrategy;
    public RateLimiterStrategyfactory(String strategy){
        if(strategy=="Bucket"){
            rateStrategy=new BucketStrategy();
        }
        else if(strategy=="Leaky")
            rateStrategy=new LeakyStrategy();
        else 
            rateStrategy=new BucketStrategy();

     }
     
    public RateLimiterStrategy  build(){
        return rateStrategy;
    }
}
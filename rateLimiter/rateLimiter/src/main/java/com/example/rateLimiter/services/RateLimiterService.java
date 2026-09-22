package com.example.rateLimiter.services;
import org.springframework.stereotype.Service;
import com.example.rateLimiter.models.*;
@Service
public class RateLimiterService{
    public RateLimiter rateLimiter;
    public RateLimiterService(){

        this.rateLimiter=new RateLimiter(10,"Bucket");

    }
    
    public String addRequest(Request req){
        rateLimiter.addRequests(req);
        return limitRequest();

    }

    public String limitRequest(){
       return rateLimiter.limitRequest();

    }


}
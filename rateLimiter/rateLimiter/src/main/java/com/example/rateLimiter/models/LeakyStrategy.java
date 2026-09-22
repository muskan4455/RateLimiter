package com.example.rateLimiter.models;

import java.util.ArrayList;

public class  LeakyStrategy implements RateLimiterStrategy {

    public String limitRequest(ArrayList<Request>requests, int capacity){
        if(requests.size()>5)return "Rate Limited Followed by LeakyStrategy";
        System.out.println("Followed LeakyStrategy");
        return "Next Allowed";
    }
}
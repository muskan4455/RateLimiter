package com.example.rateLimiter.models;

import java.util.ArrayList;

class BucketStrategy implements RateLimiterStrategy{
    public String limitRequest(ArrayList<Request>requests, int capacity) {
         if(requests.size()>5)return "Rate Limited Followed by BucketStrategy";
         return "Next Allowed by BucketStrategy";
    }
}
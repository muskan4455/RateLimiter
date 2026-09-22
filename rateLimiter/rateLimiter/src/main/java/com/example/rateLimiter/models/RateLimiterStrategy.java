package com.example.rateLimiter.models;

import java.util.ArrayList;

/**
 * RateLimiterStrategy
 */
public interface RateLimiterStrategy {

   public default String limitRequest(ArrayList<Request>requests,int capacity){
            return "Default";
   };
}
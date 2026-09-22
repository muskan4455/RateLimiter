    package com.example.rateLimiter.controller;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import com.example.rateLimiter.services.RateLimiterService;
    import com.example.rateLimiter.models.*;

    @RestController
    @RequestMapping("/RateLimiter")
    public  class RateLimiterController{
       public  RateLimiterService service;
        
        public RateLimiterController(){
            this.service=new RateLimiterService();
        }

        @GetMapping("/AddRequest")
        public String addRequests( @RequestBody  Request req){
            return service.addRequest(req);
        }

        @GetMapping("/LimitRequest")
        public String limitRequest(){
            return service.limitRequest();
        }

    }
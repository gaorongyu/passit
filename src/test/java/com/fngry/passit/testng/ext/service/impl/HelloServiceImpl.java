package com.fngry.passit.testng.ext.service.impl;

import com.fngry.passit.testng.ext.service.TimeService;
import com.fngry.passit.testng.ext.service.request.HelloRequest;
import org.springframework.util.StringUtils;

public class HelloServiceImpl {

    private String now;

    private TimeService timeService = new TimeServiceImpl(new ExtTimeServiceImpl());

    public String sayHello(HelloRequest request) {
        String name = request.getFirstPart();
        if (!StringUtils.isEmpty(this.now)) {
            name = this.now;
        }
        return "Good " + timeService.now(name) + ", "
                + request.getFirstPart() + " " + request.getSecondPart();
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
}

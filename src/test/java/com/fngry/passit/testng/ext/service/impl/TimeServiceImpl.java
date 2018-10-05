package com.fngry.passit.testng.ext.service.impl;

import com.fngry.passit.testng.ext.service.ExtTimeService;
import com.fngry.passit.testng.ext.service.TimeService;
import org.springframework.util.StringUtils;

public class TimeServiceImpl implements TimeService {

    private ExtTimeService extTimeService;

    public TimeServiceImpl(ExtTimeService extTimeService) {
        this.extTimeService = extTimeService;
    }

    @Override
    public String now(String name) {
        return extTimeService.now(name);
    }

}

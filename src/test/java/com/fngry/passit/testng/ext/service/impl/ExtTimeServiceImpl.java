package com.fngry.passit.testng.ext.service.impl;

import com.fngry.passit.testng.ext.service.ExtTimeService;

import java.util.Date;

public class ExtTimeServiceImpl implements ExtTimeService {
    @Override
    public String now(String name) {
        return name;
    }
}

package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCaseExpectation;
import com.fngry.passit.testng.ext.TestCaseMockInvocation;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCaseMockInvocationImpl implements TestCaseMockInvocation {

    @Override
    public TestCaseExpectation<List<?>> getArgs() {
        return null;
    }

    @Override
    public Object getReturn() {
        return null;
    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public int getTimes() {
        return 0;
    }

    @Override
    public Actual dequeue(long timeout, TimeUnit timeUnit) throws Exception {
        return null;
    }

    @Override
    public int getInvokeCount() {
        return 0;
    }

    @Override
    public boolean acceptMoreInvocation() {
        return false;
    }

    @Override
    public synchronized Object execute(List<?> args) throws Exception {
        return null;
    }

}

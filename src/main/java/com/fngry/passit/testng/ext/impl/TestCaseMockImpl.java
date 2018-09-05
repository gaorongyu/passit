package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCaseMock;
import com.fngry.passit.testng.ext.TestCaseMockInvocation;

import java.util.ArrayList;
import java.util.List;

public class TestCaseMockImpl implements TestCaseMock {

    private List<TestCaseMockInvocation> invocations = new ArrayList<>();

    private String name;

    private int times;

    private int invokeCount;

    public TestCaseMockImpl(String name, Object conf) {
        // todo
    }

    @Override
    public int getInvokeCount() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<? extends TestCaseMockInvocation> getInvocations() {
        return null;
    }
}

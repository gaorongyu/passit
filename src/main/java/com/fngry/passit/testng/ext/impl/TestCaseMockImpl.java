package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCaseMock;
import com.fngry.passit.testng.ext.TestCaseMockInvocation;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestCaseMockImpl implements TestCaseMock {

    private List<TestCaseMockInvocation> invocations = new ArrayList<>();

    private String name;

    private int times;

    private int invokeCount;

    public TestCaseMockImpl(String name, Object conf) {
        // todo
        this.name = name;

        if (conf instanceof Map) {
            TestCaseMockInvocationImpl invocation = new TestCaseMockInvocationImpl(name, 0, (Map<String, Object>) conf);
            invocations.add(invocation);
            times = invocation.getTimes();
        } else if (conf instanceof List) {
            int time = 0;
            int seq = 0;
            for (Object obj : (List) conf) {
                TestCaseMockInvocationImpl invocation = new TestCaseMockInvocationImpl(name, seq, (Map<String, Object>) obj);
                invocations.add(invocation);
                seq ++;
            }
            this.times = time;
        } else {
            throw new RuntimeException("Mock is not Map or List type");
        }
    }

    @Override
    public int getInvokeCount() {
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<? extends TestCaseMockInvocation> getInvocations() {
        return this.invocations;
    }

    @Override
    public Object invoke(Object[] args) throws Exception {
        return invoke(Arrays.asList(args));
    }

    public synchronized Object invoke(List<?> args) throws Exception {
        int index = this.invokeCount;
        TestCaseMockInvocation invocation = invocation(index);

        if (invocation == null) {
            Assert.fail("Mock invoke too many times: " + name);
        }
        return invocation.execute(args);
    }

    private TestCaseMockInvocation invocation(int index) {
        int invokeCount = 0;

        for (TestCaseMockInvocation invocation : invocations) {
            invokeCount = invokeCount + invocation.getInvokeCount();
            if (!invocation.acceptMoreInvocation()) {
                continue;
            }
            if (invokeCount == index) {
                return invocation;
            }
        }
        return null;
    }

    @Override
    public void verify() {
        invocations.forEach(e -> e.verify());
    }

}

package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.*;
import org.testng.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestCaseImpl extends TestCases implements TestCase {

    private final Object testInstance;

    private final Map<String, Object> outputs = new ConcurrentHashMap<String, Object>();

    private final Map<String, TestCaseMock> mocks;

    private boolean started;

    private boolean completed;

    private TestCaseConfig testCaseConfig;

    private Map<String, TestCaseExpectation<?>> expectations = new HashMap<>();


    public TestCaseImpl(TestCaseConfig testCaseConfig, Object testInstance) {
        this.testCaseConfig = testCaseConfig;
        this.testInstance = testInstance;
        this.mocks = initMocks();
    }

    private Map<String, TestCaseMock> initMocks() {
        if (this.testCaseConfig.getData().getMocks().isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, TestCaseMock> result = new HashMap<>();

        this.testCaseConfig.getData().getMocks().forEach((k, v) -> {
            if (TestCaseImpl.isPossibleMock(k, v)) {
                result.put(k, new TestCaseMockImpl(k, v));
            }
        });

        return result;
    }

    private static boolean isPossibleMock(String k, Object v) {
        // todo v contains return, throw, args
        return true;
    }


    @Override
    public String getUuid() {
        return this.testCaseConfig.getMeta().uuid;
    }

    @Override
    public String getCaseId() {
        return this.testCaseConfig.getData().getCaseId();
    }

    @Override
    public String getDescription() {
        return this.testCaseConfig.getData().getDescription();
    }

    @Override
    public String getCaseGroup() {
        return this.testCaseConfig.getMeta().getCaseGroup();
    }

    @Override
    public Object getInput(String name) {
        return getInput(name, (Object) null);
    }

    @Override
    public <T> T getInput(String name, Class<T> type) {
        return (T) getInput(name);
    }

    @Override
    public <T> T getInput(String name, T defaultValue) {
        if (this.testCaseConfig.getData().getInputs().containsKey(name)) {
            return (T) this.testCaseConfig.getData().getInputs().get(name);
        } else {
            return defaultValue;
        }
    }

    @Override
    public <T> T getInput(Class<T> type) {
        StringBuffer name = new StringBuffer(type.getSimpleName());
        name.setCharAt(0, Character.toLowerCase(name.charAt(0)));
        return getInput(name.toString(), type);
    }

    @Override
    public boolean isInputPresent(String name) {
        return this.testCaseConfig.getData().getInputs().containsKey(name);
    }

    @Override
    public void setOutput(String name, Object value) {
        this.outputs.put(name, value);
    }

    @Override
    public <T> T getOutput(String name, Class<T> type) {
        return (T) getInput(name);
    }

    @Override
    public <T> T getOutput(String name) {
        return (T) this.outputs.get(name);
    }

    @Override
    public <T> TestCaseExpectation<T> getExpectation(String name, Class<T> type) {
        return (TestCaseExpectation<T>) getExpectation(name);
    }

    public synchronized TestCaseExpectation<?> getExpectation(String name) {
        TestCaseExpectation<?> result = expectations.get(name);
        if (result != null) {
            return result;
        }

        result = new TestCaseExpectationImpl<>(this, name, isExpectationPresent(name),
                this.testCaseConfig.getData().getExpectations().get(name));

        expectations.put(name, result);
        return result;
    }

    @Override
    public void verifyExpectation(String name, Object actual) {
        getExpectation(name, Object.class).verify(actual);
    }

    @Override
    public synchronized TestCaseMock getMock(String name) {
        return mocks.get(name);
    }

    @Override
    public boolean isExpectationPresent(String name) {
        return this.testCaseConfig.getData().getExpectations().containsKey(name);
    }

    @Override
    public Object getTestInstance() {
        return this.testInstance;
    }

    @Override
    public TestCaseRunner newChild(String name) {
        return null;
    }


    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;

        push(this);
    }

    public void complete() {
        if (this.completed) {
            return;
        }
        this.completed = true;

        TestCase pop = pop();
        Assert.assertSame(pop, this, "Illegal state of TestCase Stack");
    }

    public void verifyMocks() {
        this.mocks.values().forEach(e -> e.verify());
    }

    public TestCaseConfig getTestCaseConfig() {
        return this.testCaseConfig;
    }

    public Map<String, Object> getOutputs() {
        return this.outputs;
    }

    public String toString() {
        return this.testCaseConfig.getData().getCaseId();
    }

}

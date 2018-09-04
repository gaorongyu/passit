package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseExpectation;
import com.fngry.passit.testng.ext.TestCaseMock;
import com.fngry.passit.testng.ext.TestCaseRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestCaseImpl implements TestCase {

    private final Object testInstance;

    private final Map<String, Object> outputs = new ConcurrentHashMap<String, Object>();

    private final Map<String, TestCaseMock> mocks;

    private boolean started;

    private boolean completed;

    private TestCaseConfig testCaseConfig;


    public TestCaseImpl(TestCaseConfig testCaseConfig, Object testInstance) {
        this.testCaseConfig = testCaseConfig;
        this.testInstance = testInstance;
        this.mocks = initMocks();
    }

    private Map<String, TestCaseMock> initMocks() {
        return null;
    }


    @Override
    public String getUuid() {
        return null;
    }

    @Override
    public String getCaseId() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getCaseGroup() {
        return null;
    }

    @Override
    public Object getInput(String name) {
        return null;
    }

    @Override
    public <T> T getInput(String name, Class<T> type) {
        return null;
    }

    @Override
    public <T> T getInput(String name, T defaultValue) {
        return null;
    }

    @Override
    public <T> T getInput(Class<T> type) {
        return null;
    }

    @Override
    public boolean isInputPresent(String name) {
        return false;
    }

    @Override
    public void setOutput(String name, Object value) {

    }

    @Override
    public <T> T getOutput(String name, Class<T> type) {
        return null;
    }

    @Override
    public <T> T getOutput(String name) {
        return null;
    }

    @Override
    public <T> TestCaseExpectation<T> getExpectation(String name, Class<T> type) {
        return null;
    }

    @Override
    public void verifyExpectation(String name, Object actual) {

    }

    @Override
    public TestCaseMock getMock(String name) {
        return null;
    }

    @Override
    public boolean isExpectationPresent(String name) {
        return false;
    }

    @Override
    public Object getTestInstance() {
        return null;
    }

    @Override
    public TestCaseRunner newChild(String name) {
        return null;
    }

}

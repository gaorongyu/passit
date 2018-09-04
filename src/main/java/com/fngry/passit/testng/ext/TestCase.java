package com.fngry.passit.testng.ext;

public interface TestCase {

    String getUuid();

    String getCaseId();

    String getDescription();

    String getCaseGroup();

    Object getInput(String name);

    <T> T getInput(String name, Class<T> type);

    <T> T getInput(String name, T defaultValue);

    <T> T getInput(Class<T> type);

    boolean isInputPresent(String name);

    void setOutput(String name, Object value);

    <T> T getOutput(String name, Class<T> type);

    <T> T getOutput(String name);

    <T> TestCaseExpectation<T> getExpectation(String name, Class<T> type);

    void verifyExpectation(String name, Object actual);

    TestCaseMock getMock(String name);

    boolean isExpectationPresent(String name);

    Object getTestInstance();

    TestCaseRunner newChild(String name);



}

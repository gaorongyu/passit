package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.impl.TestCaseConfig;

import java.util.Map;

/**
 * test case
 * @author gaorongyu
 */
public interface TestCase {

    /**
     * get uuid
     * @return
     */
    String getUuid();

    /**
     * get case id
     * @return
     */
    String getCaseId();

    /**
     * get description
     * @return
     */
    String getDescription();

    /**
     * get case group
     * @return
     */
    String getCaseGroup();

    /**
     * get input by name
     * @param name
     * @return
     */
    Object getInput(String name);

    /**
     * get input by name and type
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    <T> T getInput(String name, Class<T> type);

    /**
     * get input by name, return default value if not exists
     * @param name
     * @param defaultValue
     * @param <T>
     * @return
     */
    <T> T getInput(String name, T defaultValue);

    /**
     * get input by type
     * @param type
     * @param <T>
     * @return
     */
    <T> T getInput(Class<T> type);

    /**
     * whether input exists
     * @param name
     * @return
     */
    boolean isInputPresent(String name);

    /**
     * set output
     * @param name
     * @param value
     */
    void setOutput(String name, Object value);

    /**
     * get output by name and type
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    <T> T getOutput(String name, Class<T> type);

    /**
     * get output by name
     * @param name
     * @param <T>
     * @return
     */
    <T> T getOutput(String name);

    /**
     * get expectation by name and type
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    <T> TestCaseExpectation<T> getExpectation(String name, Class<T> type);

    /**
     * get expectation by name
     * @param name
     * @return
     */
    TestCaseExpectation<?> getExpectation(String name);

    /**
     * verify expectation
     * @param name
     * @param actual
     */
    void verifyExpectation(String name, Object actual);

    /**
     * get mock by name
     * @param name
     * @return
     */
    TestCaseMock getMock(String name);

    /**
     * whether expectation specified by name exists
     * @param name
     * @return
     */
    boolean isExpectationPresent(String name);

    /**
     * get test intance
     * @return
     */
    Object getTestInstance();

    /**
     * new child
     * @param name
     * @return
     */
    TestCaseRunner newChild(String name);

    /**
     *  verify mocks
     */
    void verifyMocks();

    /**
     * get case config
     * @return
     */
    TestCaseConfig getTestCaseConfig();

    /**
     * get outputs
     * @return
     */
    Map<String, Object> getOutputs();

}

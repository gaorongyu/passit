package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseRunner;

import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author gaorongyu
 */
public class TestCaseRunnerImpl implements TestCaseRunner {

    private TestCase testCase;

    @Override
    public Map<String, Object> run(Consumer<TestCase> consumer) {
        consumer.accept(testCase);
        testCase.verifyMocks();
        return testCase.getOutputs();
    }

}

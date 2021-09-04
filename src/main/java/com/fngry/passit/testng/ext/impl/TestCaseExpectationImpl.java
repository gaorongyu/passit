package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.Predictive;
import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseExpectation;
import org.testng.Assert;

import java.util.function.Function;

/**
 *
 * TestCase expectation
 *
 * @param <T>
 *
 * @author gaorongyu
 */
public class TestCaseExpectationImpl<T> implements TestCaseExpectation<T> {

    private final TestCase testCase;

    private final String name;

    private final boolean present;

    private final T expectedValue;


    public TestCaseExpectationImpl(TestCase testCase, String name, boolean present, T expectedValue) {
        this.testCase = testCase;
        this.name = name;
        this.present = present;
        this.expectedValue = expectedValue;
    }

    @Override
    public T get() {
        return expectedValue;
    }

    @Override
    public boolean isPresent() {
        return present;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void verify(T actual) {
        verify(actual, expectedValue, name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void predicateVerify(Object actual) {
        if (Predictive.class.isAssignableFrom(expectedValue.getClass())) {
            Predictive predictive = (Predictive) expectedValue;
            try {
                predictive.compile();
            } catch (Exception e) {
                Assert.fail(name + ": " + e.getMessage(), e);
            }
            predictive.verify(actual, name);
        }
    }

    public static Object verify(Object actual, Object expectedValue, String name) {
        if (expectedValue == null) {
            Assert.assertNotNull(actual, name);
            return actual;
        }

        if (actual == null) {
            Assert.assertNotNull(actual, name);
            return actual;
        }

        Assert.assertEquals(actual, expectedValue, name);
        return actual;
    }

}

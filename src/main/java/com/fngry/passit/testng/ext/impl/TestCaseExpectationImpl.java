package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.Predictive;
import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseExpectation;
import org.testng.Assert;

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

        if (Predictive.class.isAssignableFrom(expectedValue.getClass())) {
            try {
                ((Predictive) expectedValue).compile();
            } catch (Exception e) {
                Assert.fail(name + ": " + e.getMessage(), e);
            }
            return ((Predictive) expectedValue).verify(actual, name);
        }

        Assert.assertEquals(actual, expectedValue, name);
        return actual;
    }

}

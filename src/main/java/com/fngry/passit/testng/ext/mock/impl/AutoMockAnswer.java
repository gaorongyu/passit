package com.fngry.passit.testng.ext.mock.impl;

import com.fngry.passit.testng.ext.TestCase;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AutoMockAnswer implements Answer<Object> {

    public static final AutoMockAnswer INSTANCE = new AutoMockAnswer();

    private final TestCase testCase;

    private final Class<?> clazz;

    public AutoMockAnswer() {
        this(null);
    }

    public AutoMockAnswer(Class<?> clazz) {
        this(clazz, null);
    }

    public AutoMockAnswer(Class<?> clazz, TestCase testCase) {
        this.clazz = clazz;
        this.testCase = testCase;
    }

    @Override
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        return null;
    }
}

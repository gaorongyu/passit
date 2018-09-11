package com.fngry.passit.testng.ext.mock.impl;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseMock;
import com.fngry.passit.testng.ext.TestCases;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;

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
        return answer(invocationOnMock.getMethod(), invocationOnMock.getArguments(), this.testCase, this.clazz);
    }

    private Object answer(Method method, Object[] arguments, TestCase testCase, Class<?> targetClass) throws Throwable {
        TestCase tc = testCase == null ? TestCases.peek() : testCase;
        Class<?> clazz = targetClass == null ? method.getDeclaringClass() : targetClass;

        String key = simpleKey(clazz, method);
        TestCaseMock mock = tc.getMock(key);

        if (mock == null) {
            key = createKey(clazz, method);
            mock = tc.getMock(key);
        }

        if (mock == null) {
            throw new RuntimeException("mock not defined: " + key);
        }

        return mock.invoke(arguments);
    }

    private String createKey(Class<?> clazz, Method method) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(clazz.getSimpleName()).append(".").append(method.getName());
        return buffer.toString();
    }

    private String simpleKey(Class<?> clazz, Method method) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(clazz.getName()).append(".").append(method.getName());
        return buffer.toString();
    }

}

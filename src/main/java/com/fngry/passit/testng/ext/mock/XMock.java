package com.fngry.passit.testng.ext.mock;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseMock;
import com.fngry.passit.testng.ext.TestCases;
import com.fngry.passit.testng.ext.impl.TestCaseMockImpl;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class XMock<R> implements Answer<R> {

    private final TestCase testCase;

    private final String name;

    public XMock(String name, TestCase testCase) {
        this.testCase = testCase;
        this.name = name;
    }

    public R apply(Object[] args) throws Exception {
        List<?> objects = args == null || args.length == 0
                ? Collections.emptyList() : Arrays.asList(args);
        return apply(objects);
    }

    public R apply(List<?> objects) throws Exception {
        TestCase testCase = this.testCase == null ? TestCases.peek() : this.testCase;
        TestCaseMock testCaseMock = testCase.getMock(name);

        if (testCaseMock == null) {
            throw new AutoMockException("mock not defined: " + name);
        }
        return (R) ((TestCaseMockImpl) testCaseMock).in;
    }


    public R answer(InvocationOnMock invocationOnMock) throws Throwable {
        return apply(invocationOnMock.getArguments());
    }

}

package com.fngry.passit.testng.ext;

import java.util.Stack;

public class TestCases {

    private static ThreadLocal<Stack<TestCase>> TEST_CASES_STACK = ThreadLocal.withInitial(() -> new Stack<>());

    protected TestCases() {

    }

    protected static void push(TestCase testCase) {
        TEST_CASES_STACK.get().push(testCase);
    }

    protected static TestCase pop() {
        return TEST_CASES_STACK.get().pop();
    }

    public static TestCase peek() {
        Stack<TestCase> testCases = TEST_CASES_STACK.get();
        if (testCases.isEmpty()) {
            return null;
        }
        return testCases.peek();
    }

}

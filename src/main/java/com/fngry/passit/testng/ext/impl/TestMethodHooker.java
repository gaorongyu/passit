package com.fngry.passit.testng.ext.impl;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class TestMethodHooker implements IHookable {

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {

    }

    public static IHookCallBack wrap(IHookCallBack iHookCallBack) {
        TestCaseImpl testCase = testCase(iHookCallBack.getParameters());
        if (testCase == null) {
            return iHookCallBack;
        }

        return new IHookCallBack() {
            @Override
            public void runTestMethod(ITestResult iTestResult) {
                testCase.start();

                try {
                    iHookCallBack.runTestMethod(iTestResult);
                } finally {
                    if (iTestResult.getThrowable() == null) {
                        testCase.verfyMocks();
                    }
                    testCase.complete();
                }
            }

            @Override
            public Object[] getParameters() {
                return iHookCallBack.getParameters();
            }
        };
    }

    protected static TestCaseImpl testCase(Object[] parameters) {
        if (parameters.length == 1 && TestCaseImpl.class == parameters[0].getClass()) {
            return (TestCaseImpl) parameters[0];
        }
        return null;
    }

}

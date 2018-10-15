package com.fngry.passit.testng.ext.impl;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 *
 * @author gaorongyu
 */
public class TestMethodListener implements IInvokedMethodListener {


    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        TestCaseImpl testCase = TestMethodHooker.testCase(iTestResult.getParameters());

        if (testCase == null) {
            return;
        }

        if (!iTestResult.isSuccess()) {
            System.out.println("error:" + testCase.getTestCaseConfig().toString());
        }
    }

}

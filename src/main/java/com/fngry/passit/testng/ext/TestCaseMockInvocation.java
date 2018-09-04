package com.fngry.passit.testng.ext;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface TestCaseMockInvocation {

    TestCaseExpectation<List<?>> getArgs();

    Object getReturn();

    Throwable getException();

    int getTimes();

    Actual dequeue(long timeout, TimeUnit timeUnit) throws Exception;


    interface Actual {

        List<?> getArgs();

        Object getReturn();

        Throwable getException();

        void verify();

    }

}

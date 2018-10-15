package com.fngry.passit.testng.ext;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * mock invocation
 * @author gaorongyu
 */
public interface TestCaseMockInvocation {

    /**
     * args
     * @return
     */
    TestCaseExpectation<List<?>> getArgs();

    /**
     * return
     * @return
     */
    Object getReturn();

    /**
     * exception
     * @return
     */
    Throwable getException();

    /**
     * times
     * @return
     */
    int getTimes();

    /**
     * add to queue
     * @param actual
     */
    void enqueue(Actual actual);

    /**
     * out queue
     * @param timeout
     * @param timeUnit
     * @return
     * @throws Exception
     */
    Actual dequeue(long timeout, TimeUnit timeUnit) throws Exception;

    /**
     * get invoke cnt
     * @return
     */
    int getInvokeCount();

    /**
     * whether can accept more
     * @return
     */
    boolean acceptMoreInvocation();

    /**
     * execute
     * @param arguments
     * @return
     * @throws Exception
     */
    Object execute(List<?> arguments) throws Exception;

    /**
     * verify
     */
    void verify();


    interface Actual {

        List<?> getArgs();

        Object getReturn();

        Throwable getException();

        void verify();

    }

}

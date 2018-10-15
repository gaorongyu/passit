package com.fngry.passit.testng.ext;

import java.util.concurrent.Future;

/**
 * expectation
 * @author gaorongyu
 */
public interface TestCaseExpectation<T> {

    /**
     * get expectation
     * @return
     */
    T get();

    /**
     * whether exists or not
     * @return
     */
    boolean isPresent();

    /**
     * get name
     * @return
     */
    String getName();

    /**
     * verify whether the real result equals the result expected
     * @param actual
     */
    void verify(T actual);

    default void verify(Future<T> future) throws Exception {
        verify(future);
    }

}

package com.fngry.passit.testng.ext;

import java.util.concurrent.Future;

public interface TestCaseExpectation<T> {

    T get();

    boolean isPresent();

    String getName();

    void verify(T actual);

    default void verify(Future<T> future) throws Exception {
        verify(future);
    }

}

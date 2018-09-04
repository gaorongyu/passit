package com.fngry.passit.testng.ext;

public interface Predictive<T> {

    void compile() throws Exception;

    Object verify(T actual, String expectationKey);

}

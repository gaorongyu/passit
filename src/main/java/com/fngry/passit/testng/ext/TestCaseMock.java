package com.fngry.passit.testng.ext;

import java.util.List;

public interface TestCaseMock {

    int getInvokeCount();

    String getName();

    List<? extends TestCaseMockInvocation> getInvocations();

    Object invoke(Object[] args) throws Exception;

    void verify();

}

package com.fngry.passit.testng.ext;

import java.util.List;

/**
 *
 * @author gaorongyu
 */
public interface TestCaseMock {

    /**
     * invoke cnt
     * @return
     */
    int getInvokeCount();

    /**
     * get name
     * @return
     */
    String getName();

    /**
     * get invocations
     * @return
     */
    List<? extends TestCaseMockInvocation> getInvocations();

    /**
     * do invoke
     * @param args
     * @return
     * @throws Exception
     */
    Object invoke(Object[] args) throws Exception;

    /**
     * do verify
     */
    void verify();

}

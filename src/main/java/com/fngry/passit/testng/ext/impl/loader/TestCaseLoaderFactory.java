package com.fngry.passit.testng.ext.impl.loader;

import java.lang.reflect.Method;

/**
 * testCase loader factory
 *
 * @author gaorongyu
 */
public interface TestCaseLoaderFactory {

    /**
     * create test case loader
     * @param method
     * @param testInstance
     * @return
     * @throws Exception
     */
    TestCaseLoader newInstance(Method method, Object testInstance) throws Exception;

}

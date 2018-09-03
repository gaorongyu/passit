package com.fngry.passit.testng.ext.impl.loader;

import java.lang.reflect.Method;

/**
 * testCase loader factory
 *
 * @author gaorongyu
 */
public interface TestCaseLoaderFactory {

    TestCaseLoader newInstance(Method method, Object testInstance) throws Exception;

}

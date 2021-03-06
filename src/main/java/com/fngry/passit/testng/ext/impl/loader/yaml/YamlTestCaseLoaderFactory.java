package com.fngry.passit.testng.ext.impl.loader.yaml;

import com.fngry.passit.testng.ext.impl.loader.TestCaseLoader;
import com.fngry.passit.testng.ext.impl.loader.TestCaseLoaderFactory;

import java.lang.reflect.Method;

/**
 *
 * @author gaorongyu
 */
public class YamlTestCaseLoaderFactory implements TestCaseLoaderFactory {

    @Override
    public TestCaseLoader newInstance(Method method, Object testInstance) throws Exception {
        return new YamlTestCaseLoader(method, testInstance);
    }

}

package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.impl.TestCaseConfig;
import com.fngry.passit.testng.ext.impl.TestCaseImpl;
import com.fngry.passit.testng.ext.impl.loader.TestCaseLoader;
import com.google.common.collect.AbstractIterator;
import org.testng.annotations.TestInstance;

import java.lang.reflect.Method;
import java.util.Iterator;

public class TestCaseDataProvider {

    public static Iterator<Object[]> load(Method testMethod, @TestInstance Object testInstance) throws Exception {
        Class<? extends TestCaseDataProvider> factoryClass = getFactoryClass(testMethod);

        TestCaseLoader testCaseLoader = newTestCaseLoader(factoryClass, testMethod, testInstance);
        Iterator<TestCaseConfig> testCases = testCaseLoader.load();

        return new TestCaseIterator(testCases, testInstance);
    }

    private static TestCaseLoader newTestCaseLoader(Class<? extends TestCaseDataProvider> factoryClass,
            Method testMethod, Object testInstance) {
        return null;
    }

    private static Class<? extends TestCaseDataProvider> getFactoryClass(Method testMethod) {
        return null;
    }

    private static class TestCaseIterator extends AbstractIterator<Object[]> {

        private Iterator<TestCaseConfig> testCaseConfigs;

        private Object testInstance;


        public TestCaseIterator(Iterator<TestCaseConfig> testCaseConfigs, Object testInstance) {
            this.testCaseConfigs = testCaseConfigs;
            this.testInstance = testInstance;
        }

        protected Object[] computeNext() {
            while (testCaseConfigs.hasNext()) {
                TestCaseConfig config = testCaseConfigs.next();

                return new Object[] { new TestCaseImpl(config, testInstance) };
            }
            return endOfData();
        }
    }

}

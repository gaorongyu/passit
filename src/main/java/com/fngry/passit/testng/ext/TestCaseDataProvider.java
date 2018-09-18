package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.impl.TestCaseConfig;
import com.fngry.passit.testng.ext.impl.TestCaseImpl;
import com.fngry.passit.testng.ext.impl.loader.TestCaseLoader;
import com.fngry.passit.testng.ext.impl.loader.TestCaseLoaderFactory;
import com.fngry.passit.testng.ext.impl.loader.yaml.YamlTestCaseLoaderFactory;
import com.google.common.collect.AbstractIterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.TestInstance;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestCaseDataProvider {

    private static Map<Class<? extends TestCaseLoaderFactory>, TestCaseLoaderFactory> factories = new HashMap<>();

    @DataProvider(name = "default")
    public static Iterator<Object[]> load(Method testMethod, @TestInstance Object testInstance) throws Exception {
        Class<? extends TestCaseLoaderFactory> factoryClass = getFactoryClass(testMethod);

        TestCaseLoader testCaseLoader = newTestCaseLoader(factoryClass, testMethod, testInstance);
        Iterator<TestCaseConfig> testCases = testCaseLoader.load();

        return new TestCaseIterator(testCases, testInstance);
    }

    private static synchronized TestCaseLoader newTestCaseLoader(Class<? extends TestCaseLoaderFactory> factoryClass,
            Method testMethod, Object testInstance) throws Exception {
        TestCaseLoaderFactory factory = factories.get(factoryClass);
        if (factory == null) {
            factory = factoryClass.newInstance();
            factories.put(factory.getClass(), factory);
        }
        return factory.newInstance(testMethod, testInstance);
    }

    private static Class<? extends TestCaseLoaderFactory> getFactoryClass(Method testMethod) {
        TestCaseLoaderFactoryClass annotation = testMethod.getAnnotation(TestCaseLoaderFactoryClass.class);
        Class<? extends TestCaseLoaderFactory> factoryClass = annotation != null
                ? annotation.value() : YamlTestCaseLoaderFactory.class;
        return factoryClass;
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

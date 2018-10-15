package com.fngry.passit.testng.ext.impl.loader.yaml;

import com.fngry.passit.testng.ext.impl.TestCaseConfig;
import com.fngry.passit.testng.ext.impl.TestCaseData;
import com.fngry.passit.testng.ext.impl.TestCaseMeta;
import com.fngry.passit.testng.ext.impl.loader.TestCaseLoader;
import com.google.common.collect.AbstractIterator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * test case loader
 *  load test case config
 *
 * @author gaorongyu
 */
public class YamlTestCaseLoader implements TestCaseLoader {

    public static final String CASE_ID = "caseId";

    public static final String DESCRIPTION = "description";

    public static final String INPUTS = "inputs";

    public static final String MOCKS = "mocks";

    public static final String EXPECTATIONS = "expectations";


    private final Method testMethod;

    private final Object testInstance;

    public YamlTestCaseLoader(Method testMethod, Object testInstance) {
        this.testMethod = testMethod;
        this.testInstance = testInstance;
    }

    @Override
    public Iterator<TestCaseConfig> load() throws Exception {
        YamlTestResource testResource = YamlTestResource.load(testMethod, testInstance);

        return new AbstractIterator<TestCaseConfig>() {

            private Iterator<YamlTestResource.YamlCaseResource> resourceIterator = testResource.caseResources.iterator();

            private YamlTestResource.YamlCaseResource currentCaseResource;

            private int caseSeq;

            private Iterator<Object> currentCaseIterator;

            @Override
            protected TestCaseConfig computeNext() {
                while (resourceIterator.hasNext() || currentCaseIterator != null) {
                    if (currentCaseIterator == null) {
                        currentCaseResource = resourceIterator.next();

                        // todo extend reader

                        caseSeq = 0;
                        currentCaseIterator = currentCaseResource.iterator();
                    }

                    while (currentCaseIterator.hasNext()) {
                        return resolve(testResource, currentCaseResource, caseSeq++, (Map) currentCaseIterator.next());
                    }

                    currentCaseIterator = null;
                }
                return endOfData();
            }
        };

    }

    private TestCaseConfig resolve(YamlTestResource testResource, YamlTestResource.YamlCaseResource caseResource,
            int caseSeq, Map context) {

        TestCaseConfig testCaseConfig = new TestCaseConfig();
        TestCaseData testCaseData = new TestCaseData();
        TestCaseMeta testCaseMeta = new TestCaseMeta();
        testCaseConfig.setData(testCaseData);
        testCaseConfig.setMeta(testCaseMeta);

        testCaseData.setCaseId((String) context.get(CASE_ID));
        testCaseData.setDescription((String) context.get(DESCRIPTION));

        testCaseMeta.setTest(testResource.test);
        testCaseMeta.setSource(caseResource.resource.getDescription());
        testCaseMeta.setSequence(caseSeq);
        testCaseMeta.setCaseGroup(caseResource.caseGroup);

        Map<String, Object> inputs = (Map) context.get(INPUTS);
        Map<String, Object> expectations = (Map) context.get(EXPECTATIONS);
        Map<String, Object> mocks = (Map) context.get(MOCKS);

        testCaseData.setInputs(inputs != null ? inputs : Collections.emptyMap());
        testCaseData.setExpectations(expectations != null ? expectations : Collections.emptyMap());
        testCaseData.setMocks(mocks != null ? mocks : Collections.emptyMap());

        return testCaseConfig;
    }

}

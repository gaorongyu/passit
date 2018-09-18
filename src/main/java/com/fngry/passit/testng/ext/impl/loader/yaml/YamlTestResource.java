package com.fngry.passit.testng.ext.impl.loader.yaml;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YamlTestResource {

    private static final String RESOURCE_PATTERN_0 = "%s/%s.%s";

    private static final String RESOURCE_PATTERN_1 = "%s/%s.%s/*";

    private static final String RESOURCE_PATTERN_2 = "%s/%s/%s";

    private static final String RESOURCE_PATTERN_3 = "%s/%s/%s/*";


    private static final String[] RESOURCE_PATTERNS = {
            RESOURCE_PATTERN_0,
            RESOURCE_PATTERN_1,
            RESOURCE_PATTERN_2,
            RESOURCE_PATTERN_3
    };

    private static final String CLASS_PATH = "classpath*:/";

    private static final String YML_SUFFIX = ".yml";

    public final String test;

    public List<YamlCaseResource> caseResources = new ArrayList<>();


    public YamlTestResource(String test) {
        this.test = test;
    }

    public static YamlTestResource load(Method testMethod, Object testInstance) throws IOException {
        YamlTestResource resource = new YamlTestResource(testInstance.getClass().getName()
                + "." + testMethod.getName());

        String packageName = testInstance.getClass().getPackage() + "." + testMethod.getName();
        String className = testInstance.getClass().getSimpleName();
        String methodName = testMethod.getName();

        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(
                YamlTestResource.class.getClassLoader());

        for (int i = 0; i < RESOURCE_PATTERNS.length; i++) {
            String pattern = RESOURCE_PATTERNS[i];
            String patternResolved = String.format(pattern, packageName, className, methodName);

            Resource[] resources = resourceResolver.getResources(CLASS_PATH + patternResolved + YML_SUFFIX);
            if (resources == null || resources.length == 0) {
                continue;
            }

            for (Resource res : resources) {
               resource.addCaseResource(pattern, res);
            }
        }

        return resource;
    }

    private void addCaseResource(String pattern, Resource res) {
        String caseGroup = null;
        if (pattern.endsWith("*")) {
            String fileName = res.getFilename();
            caseGroup = fileName.substring(0, fileName.length() - YML_SUFFIX.length());
        }
        this.caseResources.add(new YamlCaseResource(res, caseGroup, this));
    }


    public static class YamlCaseResource {

        public Resource resource;

        public String caseGroup;

        private YamlTestResource testResource;

        public YamlCaseResource(Resource res, String caseGroup, YamlTestResource testResource) {

        }

        public Iterator<Object> iterator() {
            return null;
        }

    }

}

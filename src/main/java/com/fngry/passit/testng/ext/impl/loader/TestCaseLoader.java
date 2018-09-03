package com.fngry.passit.testng.ext.impl.loader;

import com.fngry.passit.testng.ext.impl.TestCaseConfig;

import java.util.Iterator;

/**
 * testCase loader
 *
 * @author gaorongyu
 */
public interface TestCaseLoader {

    Iterator<TestCaseConfig> load() throws Exception;

}

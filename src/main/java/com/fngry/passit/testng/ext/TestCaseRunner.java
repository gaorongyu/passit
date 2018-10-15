package com.fngry.passit.testng.ext;

import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author gaorongyu
 */
public interface TestCaseRunner {

    /**
     * run test case
     * @param consumer
     * @return
     */
    Map<String, Object> run(Consumer<TestCase> consumer);

}

package com.fngry.passit.testng.ext;

import java.util.Map;
import java.util.function.Consumer;

public interface TestCaseRunner {

    Map<String, Object> run(Consumer<TestCase> consumer);

}

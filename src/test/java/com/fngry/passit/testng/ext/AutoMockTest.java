package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.mock.AutoMock;
import com.fngry.passit.testng.ext.service.TimeService;
import org.testng.annotations.Test;

public class AutoMockTest {

    @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
    public void test(TestCase testCase) {
        TimeService timeService = AutoMock.mock(TimeService.class);
        String result = timeService.now(testCase.getInput("arg", String.class));

        testCase.getExpectation("ret", String.class).verify(result);
    }

}

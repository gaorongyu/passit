package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.service.impl.HelloServiceImpl;
import com.fngry.passit.testng.ext.service.request.HelloRequest;
import org.testng.annotations.Test;

public class TestCaseDataProviderTest {

    private HelloServiceImpl helloService = new HelloServiceImpl();

    @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
    public void testHelloService(TestCase testCase) {

        HelloRequest request = testCase.getInput("request", HelloRequest.class);
        helloService.setNow(testCase.getInput("now", String.class));

        String result = helloService.sayHello(request);

        testCase.getExpectation("response", String.class).verify(result);

    }

}

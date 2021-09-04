package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.service.impl.HelloServiceImpl;
import com.fngry.passit.testng.ext.service.request.HelloRequest;
import com.fngry.passit.testng.ext.support.predicate.AnyPredicate;
import org.testng.annotations.Test;

public class GroovyMockTest {

    private HelloServiceImpl helloService = new HelloServiceImpl();

    @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
    public void testGroovyService(TestCase testCase) {

        HelloRequest request = testCase.getInput("request", HelloRequest.class);
        helloService.setNow(testCase.getInput("now", String.class));

        String result = helloService.sayHello(request);
        testCase.getExpectation("response", AnyPredicate.class).predicateVerify(result);

    }
}

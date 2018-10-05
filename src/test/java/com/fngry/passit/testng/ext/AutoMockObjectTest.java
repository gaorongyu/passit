package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.mock.AutoMock;
import com.fngry.passit.testng.ext.mock.AutoMockObject;
import com.fngry.passit.testng.ext.mock.AutoMockStub;
import com.fngry.passit.testng.ext.mock.InjectAutoMockObject;
import com.fngry.passit.testng.ext.service.ExtTimeService;
import com.fngry.passit.testng.ext.service.TimeService;
import com.fngry.passit.testng.ext.service.impl.TimeServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * test mock dependence
 *
 * @author gaorongyu
 */
public class AutoMockObjectTest {

    // mock extTimeService
    @AutoMockObject
    private ExtTimeService extTimeService;

    // timeService inject dependence extTimeService which is mocked
    @InjectAutoMockObject
    private TimeService timeService = new TimeServiceImpl(null);

    private AutoMockStub autoMockStub;

    @BeforeClass
    public void before() {
        this.autoMockStub = AutoMock.initMock(this);
    }

    @AfterClass
    public void after() {
        this.autoMockStub.destroy();
    }

    @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
    public void test(TestCase testCase) {
        String result = timeService.now(testCase.getInput("arg", String.class));
        testCase.getExpectation("response", String.class).verify(result);
    }

}

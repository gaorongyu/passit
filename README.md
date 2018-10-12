# passit

## quick start 

* add pom dependency
```xml
<dependency>
    <groupId>com.fngry.passit</groupId>
    <artifactId>passit</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
* first write TestCase method add annotation: @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
* then add config file(yaml format) for TestCase method. in config file you can define caseId, inputs, expectations, mocks
* last run TestCase method 

### examples:
### common method or interface TestCase

* TestCase code 
```java
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
```

* config file  
file directory "com.fngry.passit.testng.ext" must be same with TestCase code  
file name use TestCase ClassName + "." + testMethodName + ".yml"  
file content: 
```yaml
caseId: Hello World 1
inputs:
  request:
    !!com.fngry.passit.testng.ext.service.request.HelloRequest
    firstPart: Hello
    secondPart: World

expectations:
  response: Good Hello, Hello World

---
caseId: Hello World 2
inputs:
  now: Morning
  request:
    !!com.fngry.passit.testng.ext.service.request.HelloRequest
    firstPart: Hello
    secondPart: World

expectations:
  response: Good Morning, Hello World

```

### SpringBoot TestCase 
* TestCase code 
```java
package com.whatsegg;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCaseDataProvider;
import com.whatsegg.biz.cms.MaterialSearchService;
import com.whatsegg.biz.cms.bo.MaterialInfoSearchBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class MaterialSearchServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MaterialSearchService materialSearchService;

    @Test(dataProvider = "default", dataProviderClass = TestCaseDataProvider.class)
    public void test(TestCase testCase) {
        // 1. 从配置文件获取输入参数
        Long saleOrgId = Long.valueOf(testCase.getInput("saleOrgId", String.class));
        Long materialId = Long.valueOf(testCase.getInput("materialId", String.class));

        // 2. 调用待测试的方法并获取结果
        MaterialInfoSearchBO resultBO = materialSearchService.searchByMaterialId(saleOrgId, materialId, null);

        // 3. 校验返回结果和配置文件的预期结果是否一致
        // MaterialInfoSearchBO 需重写equals, 否则测试代码中需要写对比规则
        testCase.getExpectation("response", MaterialInfoSearchBO.class).verify(resultBO);
    }

}

```

* config file  
file content: 
```yaml
caseId: query material

inputs:
  saleOrgId: "6"
  materialId: "1594458"

expectations:
  response: !!com.whatsegg.biz.cms.bo.MaterialInfoSearchBO
    saleOrgId: 6
    materialId: 1594458
    eggSku: "53878-89102"
    materialType: 1
    businessType: 1
    firstEggCategoryId: 1
    secondEggCategoryId: 19
    thirdEggCategoryId: 1407
    brandName: "Toyota"
    brandId: 242947
    weightUnit: 0
    enabled: true
    oeList:
      - {
        oeId: 660018,
        oeNumber: "53878-89102"
      }
```

### use mocks
* TestCase Code  
when method A dependent interface B (local or remote), you can mock result of B with data configured in file  
```java
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
```

* config file
```yaml
caseId: test_01
inputs:
  arg: Hello
expectations:
  response: World Ext
mocks:
  ExtTimeService.now:
    args:
      - Hello
    return:
      World Ext
---
caseId: test_02
inputs:
  arg: Hello
expectations:
  response: Big World
mocks:
  ExtTimeService.now:
    args:
      - Hello
    return:
      Big World
```


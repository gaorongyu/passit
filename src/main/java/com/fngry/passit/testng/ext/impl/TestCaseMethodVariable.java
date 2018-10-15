package com.fngry.passit.testng.ext.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author gaorongyu
 */
public class TestCaseMethodVariable {

    private final Method method;

    private final Object testInstance;

    public TestCaseMethodVariable(Method method, Object testInstance) {
        this.method = method;
        this.testInstance = testInstance;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(this.testInstance);
    }

}

package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.ConfigException;
import com.fngry.passit.testng.ext.TestCaseExpectation;
import com.fngry.passit.testng.ext.TestCaseMockInvocation;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestCaseMockInvocationImpl implements TestCaseMockInvocation {

    private static final String RETURN = "return";

    private static final String THROW = "throw";

    private static final String ARGS = "args";

    private static final String THROW_CLASS = "throwClass";

    private static final String THROW_MESSAGE = "throwMessage";

    private Object ret;

    private Exception thr;

    // todo
//    private ArgsExpectation args;

    LinkedBlockingDeque<Actual> exchanger = new LinkedBlockingDeque<>();


    private List<Actual> actuals = new ArrayList<>();

    private String toString;

    public TestCaseMockInvocationImpl(String mock, int seq, Map<String, Object> raw) {
        this.toString = mock + "[" + seq + "]";

        if (raw.containsKey(RETURN) && raw.containsKey(THROW)) {
            throw new RuntimeException("return and throw cannot define at the same time");
        }

        this.thr = resolvethrow(raw);

    }

    private Exception resolvethrow(Map<String, Object> raw) {
        Object throwConf = raw.get(THROW);

        String expectationClassName = null;
        String message = null;

        if (throwConf.getClass() == String.class) {
            expectationClassName = throwConf.getClass().getSimpleName();
        } else if (throwConf instanceof Map) {
            expectationClassName = (String) ((Map) throwConf).get(THROW_CLASS);
            message = (String) ((Map) throwConf).get(THROW_MESSAGE);
        }

        if (StringUtils.isEmpty(expectationClassName)) {
            throw new ConfigException("fail to resolve throw, class is not defined");
        }

        // reflect
        try {
            return newException(expectationClassName, message);
        } catch (Exception e) {
            throw new ConfigException("fail to create Exception: ", e);
        }

    }

    private Exception newException(String expectationClassName, String message) throws ClassNotFoundException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class exceptionClass = Class.forName(expectationClassName);
        Constructor constructor = exceptionClass.getConstructor(String.class);
        Exception exception = (Exception) constructor.newInstance(message);
        return exception;
    }

    @Override
    public TestCaseExpectation<List<?>> getArgs() {
        return null;
    }

    @Override
    public Object getReturn() {
        return this.ret;
    }

    @Override
    public Throwable getException() {
        return this.thr;
    }

    @Override
    public int getTimes() {
        return 0;
    }

    @Override
    public void enqueue(Actual actual) {
        exchanger.offer(actual);
        actuals.add(actual);
    }

    @Override
    public Actual dequeue(long timeout, TimeUnit timeUnit) throws Exception {
        return exchanger.poll(timeout, timeUnit);
    }

    @Override
    public int getInvokeCount() {
        return 0;
    }

    @Override
    public boolean acceptMoreInvocation() {
        return false;
    }

    @Override
    public synchronized Object execute(List<?> arguments) throws Exception {
        if (exchanger == null) {
            return __execute__(arguments);
        }
        return null;
    }

    private Object __execute__(List<?> arguments) throws Exception {
        if (this.thr != null) {
            throw thr;
        }
        return doReturn(arguments, null);
    }

    private Object doReturn(List<?> actualArgs, List<?> expectedArgs) {

        if (this.ret instanceof Function) {
            List<Object> all = new ArrayList<>();
            all.addAll(actualArgs);
            all.addAll(expectedArgs);

            return ((Function) this.ret).apply(all);
        }

        return this.ret;
    }

    @Override
    public void verify() {
        this.actuals.forEach(e -> e.verify());
    }


    public static class ActualImpl implements Actual {

        private boolean passed;

        private List<?> args;

        private Object result;

        private Throwable thr;

        @Override
        public List<?> getArgs() {
            return args;
        }

        @Override
        public Object getReturn() {
            return result;
        }

        @Override
        public Throwable getException() {
            return thr;
        }

        @Override
        public void verify() {
            if (this.passed) {
                return;
            }

            if (AssertionError.class.isAssignableFrom(thr.getClass())) {
                throw (AssertionError) thr;
            }
            throw new AssertionError("Unexpected Error: " + thr.getMessage(), thr);
        }

    }

}

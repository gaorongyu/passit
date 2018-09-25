package com.fngry.passit.testng.ext.impl;

import com.fngry.passit.testng.ext.TestCaseExpectation;
import com.fngry.passit.testng.ext.TestCaseMockInvocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestCaseMockInvocationImpl implements TestCaseMockInvocation {

    private static final String RETURN = "return";

    private static final String THROW = "throw";

    private static final String ARGS = "args";

    private Object ret;

    private Exception thr;

    // todo
//    private ArgsExpectation args;



    private List<ActualImpl> actuals = new ArrayList<>();

    private String toString;

    public TestCaseMockInvocationImpl(String mock, int seq, Map<String, Object> raw) {
        this.toString = mock + "[" + seq + "]";

        if (raw.containsKey(RETURN) && raw.containsKey(THROW)) {
            throw new RuntimeException("return and throw cannot define at the same time");
        }

    }

    @Override
    public TestCaseExpectation<List<?>> getArgs() {
        return null;
    }

    @Override
    public Object getReturn() {
        return null;
    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public int getTimes() {
        return 0;
    }

    @Override
    public Actual dequeue(long timeout, TimeUnit timeUnit) throws Exception {
        return null;
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
    public synchronized Object execute(List<?> args) throws Exception {
        return null;
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

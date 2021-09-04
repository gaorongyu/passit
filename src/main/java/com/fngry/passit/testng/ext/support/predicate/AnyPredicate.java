package com.fngry.passit.testng.ext.support.predicate;

import com.fngry.passit.testng.ext.Predictive;
import com.fngry.passit.testng.ext.support.GroovyFunction;
import org.testng.Assert;

import java.util.Map;

/**
 *
 * @author gaorongyu
 */
public class AnyPredicate<R> implements Predictive {

    private Map<String, Object> context;

    private String expression;


    public AnyPredicate() {

    }

    public AnyPredicate(String expression) {
        this.expression = expression;
    }

    public AnyPredicate(String expression, Map<String, Object> context) {
        this.expression = expression;
        this.context = context;
    }

    private R result;

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    @Override
    public Object compile() throws Exception {
        result = new GroovyFunction().execute(expression, context);
        return result;
    }

    @Override
    public void verify(Object actual, String expectationKey) {
        Assert.assertEquals(actual, result, expectationKey);
    }

}

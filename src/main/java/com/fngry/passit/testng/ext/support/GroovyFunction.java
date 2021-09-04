package com.fngry.passit.testng.ext.support;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.util.Map;

/**
 * groovy function, evaluate expression use groovy engine
 * @author gaorongyu
 */
public class GroovyFunction {

    private static final GroovyShell GROOVY_SHELL = new GroovyShell();

    private Script script;

    public <R> R execute(String expectations, Map<String, Object> context) {
        this.script = GROOVY_SHELL.parse(expectations);
        Binding binding = new Binding();
        binding.setProperty("context", context);
        Script targetScript;
        try {
            targetScript = InvokerHelper.createScript(script.getClass(), binding);
        } catch (Exception e) {
            throw new RuntimeException("fail to create groovy script");
        }

        return (R) targetScript.run();
    }

}

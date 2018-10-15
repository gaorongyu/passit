package com.fngry.passit.testng.ext.impl.loader.yaml;

import org.yaml.snakeyaml.constructor.Construct;

import java.lang.reflect.Method;

/**
 *
 * @author gaorongyu
 */
public interface YamlConstructFactory {

    /**
     * getName
     * @return
     */
    String getName();

    /**
     * create
     * @param method
     * @param testInstance
     * @return
     */
    Construct create(Method method, Object testInstance);

}

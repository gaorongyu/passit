package com.fngry.passit.testng.ext.impl.loader.yaml;

import org.yaml.snakeyaml.constructor.Construct;

import java.lang.reflect.Method;

public interface YamlConstructFactory {

    String getName();

    Construct create(Method method, Object testInstance);

}

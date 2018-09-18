package com.fngry.passit.testng.ext;

import com.fngry.passit.testng.ext.impl.loader.TestCaseLoaderFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestCaseLoaderFactoryClass {
    Class<? extends TestCaseLoaderFactory> value();
}

package com.fngry.passit.testng.ext.mock.impl;

import org.mockito.internal.util.reflection.FieldReader;
import org.mockito.internal.util.reflection.FieldSetter;

import java.lang.reflect.Field;
import java.util.Collection;

public class InjectAutoMockObjects {

    public static class Injection {

        public AutoMockObjects.Mock mock;

        public Field field;

        public Object target;

        public Object originalValue;

        private Injection(Field field, Object target, Object originalValue, AutoMockObjects.Mock mock) {
            this.mock = mock;
            this.field = field;
            this.target = target;
            this.originalValue = originalValue;
        }

        public void destroy() {
            new FieldSetter(target, field).set(originalValue);
        }

    }

    private InjectAutoMockObjects() {

    }


    public static Collection<Injection> process(Object testInstance, Collection<AutoMockObjects.Mock> mocks) {
        return null;
    }

    public static void process(Class<?> clazz, Object testInstance, Collection<AutoMockObjects.Mock> mocks,
            Collection<Injection> injections) {

    }

    private static void inject(AutoMockObjects.Mock mock, Collection<Object> mockDependences,
            Collection<Injection> injections) {
        mockDependences.forEach(e -> inject(mock, e, injections));
    }

    private static void inject(AutoMockObjects.Mock mock, Object mockDependence,
            Collection<Injection> injections) {
        Class<?> clazz = mockDependence.getClass();
        while (clazz != Object.class) {
            inject(mock, mockDependence, clazz, injections);
            clazz = clazz.getSuperclass();
        }

    }

    private static void inject(AutoMockObjects.Mock mock, Object mockDependence,
            Class<?> clazz, Collection<Injection> injections) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(mock.field.getType())
                    && field.getName().equals(mock.name)) {
                Injection injection = new Injection(field, mockDependence,
                        new FieldReader(mockDependence, field).read(), mock);

                new FieldSetter(mockDependence, field).set(mock.instance);
                injections.add(injection);
            }
        }
    }


}

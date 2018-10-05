package com.fngry.passit.testng.ext.mock.impl;

import com.fngry.passit.testng.ext.mock.AutoMockException;
import com.fngry.passit.testng.ext.mock.AutoMockObject;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class AutoMockObjects {

    private static final AutoMockObjects INSTANCE = new AutoMockObjects();

    private AutoMockObjects() {

    }

    public static Collection<Mock> process(Object testInstance) {
        Collection<Mock> result = new ArrayList<>();

        Class<?> clazz = testInstance.getClass();
        while (clazz != Object.class) {
            INSTANCE.process(clazz, testInstance, result);
            clazz = clazz.getSuperclass();
        }

        return result;
    }

    private void process(Class<?> clazz, Object testInstance, Collection<Mock> result) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            AutoMockObject annotation = field.getAnnotation(AutoMockObject.class);
            if (annotation == null) {
                continue;
            }

            Mock mock = createMock(annotation, field);
            try {
                new FieldSetter(testInstance, field).set(mock.instance);
            } catch (Exception e) {
                throw new AutoMockException("fail to set field " + field.getName()
                        + " annotated with " + annotation, e);
            }
            result.add(mock);
        }
    }

    private Mock createMock(AutoMockObject annotation, Field field) {
        MockSettings mockSettings = Mockito.withSettings();

        String name = "".equals(annotation.name()) ? field.getName() : annotation.name();
        mockSettings.name("AutoMock:" + name).defaultAnswer(new AutoMockAnswer(field.getType()));
        Object instance = Mockito.mock(field.getType(), mockSettings);
        return new Mock(instance, name, field);
    }


    public static class Mock {

        public final Object instance;

        public final String name;

        public final Field field;

        public Mock(Object instance, String name, Field field) {
            this.instance = instance;
            this.name = name;
            this.field = field;
        }

    }

}

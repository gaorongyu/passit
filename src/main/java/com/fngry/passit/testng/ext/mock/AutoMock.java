package com.fngry.passit.testng.ext.mock;

import com.fngry.passit.testng.ext.TestCase;
import com.fngry.passit.testng.ext.TestCases;
import com.fngry.passit.testng.ext.mock.impl.AutoMockAnswer;
import com.fngry.passit.testng.ext.mock.impl.AutoMockObjects;
import com.fngry.passit.testng.ext.mock.impl.InjectAutoMockObjects;
import org.mockito.MockSettings;
import org.mockito.internal.MockitoCore;
import org.mockito.internal.creation.MockSettingsImpl;

import java.util.Collection;

public class AutoMock {

    private static MockitoCore MC = new MockitoCore();

    public static AutoMockStub initMock(Object testInstance) {
        Collection<InjectAutoMockObjects.Injection> injections = InjectAutoMockObjects.process(testInstance,
                AutoMockObjects.process(testInstance));

        return () -> injections.forEach(InjectAutoMockObjects.Injection::destroy);
    }

    public static <T> T mock(Class<T> clazz) {
        return mock(clazz, false);
    }

    private static <T> MockSettings newMockSettings(Class<T> clazz, TestCase testCase) {
        return new MockSettingsImpl<>().defaultAnswer(new AutoMockAnswer(clazz, testCase));
    }

    public static <T> T mock(Class<T> clazz, boolean testCaseScope) {
        return MC.mock(clazz, newMockSettings(clazz, testCaseScope ? TestCases.peek() : null));
    }

    public static <R> XMock mock(String name) {
        return mock(name, false);
    }

    public static <R> XMock mock(String name, boolean testCaseScope) {
        return new XMock(name, testCaseScope ? TestCases.peek() : null);
    }

}

package com.fngry.passit.testng.ext;

/**
 *
 * @param <T>
 * @author gaorongyu
 */
public interface Predictive<T> {

    /**
     * 编译
     * @throws Exception
     */
    Object compile() throws Exception;

    /**
     * 验证
     * @param actual
     * @param expectationKey
     * @return
     */
    void verify(T actual, String expectationKey);

}

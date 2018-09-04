package com.fngry.passit.testng.ext.impl;

public class TestCaseConfig {

    private TestCaseMeta meta;

    private TestCaseData data;


    public TestCaseMeta getMeta() {
        return meta;
    }

    public void setMeta(TestCaseMeta meta) {
        this.meta = meta;
    }

    public TestCaseData getData() {
        return data;
    }

    public void setData(TestCaseData data) {
        this.data = data;
    }
}

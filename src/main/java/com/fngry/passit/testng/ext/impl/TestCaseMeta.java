package com.fngry.passit.testng.ext.impl;

import java.util.Map;
import java.util.UUID;

public class TestCaseMeta {

    private final String uuid = UUID.randomUUID().toString();

    private String testGroup;

    private String source;

    private int sequence;

    private String test;

    private Map<String, Object> extendInfo;

    public String getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(String testGroup) {
        this.testGroup = testGroup;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }
}

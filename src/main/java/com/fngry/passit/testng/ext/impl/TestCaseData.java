package com.fngry.passit.testng.ext.impl;

import java.util.Map;

/**
 * test case data object
 * @author gaorongyu
 */
public class TestCaseData {

    private String caseId;

    private String description;

    private Map<String, Object> inputs;

    private Map<String, Object> expectations;

    private Map<String, Object> mocks;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public Map<String, Object> getExpectations() {
        return expectations;
    }

    public void setExpectations(Map<String, Object> expectations) {
        this.expectations = expectations;
    }

    public Map<String, Object> getMocks() {
        return mocks;
    }

    public void setMocks(Map<String, Object> mocks) {
        this.mocks = mocks;
    }
}

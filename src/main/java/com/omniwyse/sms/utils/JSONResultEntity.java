package com.omniwyse.sms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONResultEntity<T> {

    private boolean success;
    private String message;
    private List<String> errors = new ArrayList<String>();
    private Long sequenceNumber;
    private Long totalElements;
    private Integer totalPages;
    public List<T> results;
    public Map<Integer, String> resultsMap;

    public JSONResultEntity(boolean success, String message,
            List<String> errors, List<T> results) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.results = results;
    }

    public JSONResultEntity(boolean success, String message,
            List<String> errors, Map<Integer, String> results) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.resultsMap = results;
    }
    
    public JSONResultEntity(boolean success, String message,
            List<String> errors, Long totalElements, Integer totalPages, List<T> results) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.results = results;
    }
    


    public JSONResultEntity(boolean success, String message, Object object,
            Long sequenceNumber, List<T> results) {
        this.success = success;
        this.message = message;
        this.sequenceNumber = sequenceNumber;
        this.results = results;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Map<Integer, String> getResultsMap() {
        return resultsMap;
    }

    public void setResultsMap(Map<Integer, String> resultsMap) {
        this.resultsMap = resultsMap;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}

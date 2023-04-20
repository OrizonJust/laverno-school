package ru.laverno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicResponse<T> {

    @JsonProperty(value = "data")
    private T data;

    @JsonProperty(value = "fault")
    private String fault;

    public T getData() {
        return data;
    }

    public String getFault() {
        return fault;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setFault(String fault, Object... args) {
        this.fault = String.format(fault, args);
    }
}

package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement(name="ResponseOfPwsResult")
@NoArgsConstructor
public class XMLMapper {
    private String statusCode;

    private int executionTime;
    private ResponseData responseData;

    @XmlElement(name="ResponseData")
    public ResponseData getResponseData() {
        return responseData;
    }


    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
    @XmlElement(name="StatusCode")
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @XmlElement(name="ExecutionTime")
    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }
}

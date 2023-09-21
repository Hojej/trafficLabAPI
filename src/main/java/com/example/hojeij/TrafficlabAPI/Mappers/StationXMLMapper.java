package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name="ResponseOfPwsResult")
@XmlType(propOrder = {
        "statusCode",
        "executionTime",
        "responseData"
})
public class StationXMLMapper {
    private String statusCode;

    private int executionTime;
    private StationResponseData responseData;

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

    @XmlElement(name="ResponseData")
    public StationResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(StationResponseData responseData) {
        this.responseData = responseData;
    }
}

package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.NoArgsConstructor;

@XmlRootElement(name="ResponseOfPwsResult")
@XmlType(propOrder = {
        "statusCode",
        "executionTime",
        "responseData"
})
@NoArgsConstructor
public class BusXMLMapper {
    private String statusCode;

    private int executionTime;
    private BusResponseData responseData;

    @XmlElement(name="ResponseData")
    public BusResponseData getResponseData() {
        return responseData;
    }


    public void setResponseData(BusResponseData responseData) {
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

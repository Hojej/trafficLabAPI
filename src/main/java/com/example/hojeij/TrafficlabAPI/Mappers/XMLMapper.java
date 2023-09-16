package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class XMLMapper {
    private String statusCode;

    private int executionTime;
    private List<BusDTO> resposeData;

    public List<BusDTO> getResposeData() {
        return resposeData;
    }

    public void setResposeData(List<BusDTO> resposeData) {
        this.resposeData = resposeData;
    }

    public XMLMapper(){};

    public XMLMapper(String statusCode, int executionTime, List<BusDTO> resposeData) {
        this.statusCode = statusCode;
        this.executionTime = executionTime;
        this.resposeData = resposeData;
    }


}

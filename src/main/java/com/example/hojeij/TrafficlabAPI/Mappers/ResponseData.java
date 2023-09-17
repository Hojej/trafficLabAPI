package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="ResponseData")
public class ResponseData {
    private String version;
    private String type;
    private List<JourneyPatternPointOnLine> list;

    @XmlElement(name="Version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name="Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name="JourneyPatternPointOnLine")
    public List<JourneyPatternPointOnLine> getList() {
        return list;
    }

    public void setList(List<JourneyPatternPointOnLine> list) {
        this.list = list;
    }
}

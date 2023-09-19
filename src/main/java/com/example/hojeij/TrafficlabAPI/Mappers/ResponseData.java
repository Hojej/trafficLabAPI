package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlType(name = "ResponseData", propOrder = {
        "version",
        "type",
        "list"
})
public class ResponseData {
    private String version;
    private String type;
    private List<JourneyPatternPointOnLine> list;

    @XmlElement(name="Version", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name="Type", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name="JourneyPatternPointOnLine", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public List<JourneyPatternPointOnLine> getList() {
        return list;
    }

    public void setList(List<JourneyPatternPointOnLine> list) {
        this.list = list;
    }
}

package com.example.hojeij.TrafficlabAPI.Mappers;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.NoArgsConstructor;

import java.util.Date;

@XmlType(name = "JourneyPatternPointOnLine", propOrder = {
        "lineNumber",
        "directionCode",
        "journeyPatternPointNumber",
        "lastModifiedUtcDateTime",
        "existsFromDate"
})
@NoArgsConstructor
public class JourneyPatternPointOnLine {
    private int lineNumber;
    private int directionCode;
    private String journeyPatternPointNumber;
    private Date lastModifiedUtcDateTime;
    private Date existsFromDate;

    @XmlElement(name = "LineNumber", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @XmlElement(name = "DirectionCode", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public int getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(int directionCode) {
        this.directionCode = directionCode;
    }

    @XmlElement(name = "JourneyPatternPointNumber", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

    @XmlElement(name = "LastModifiedUtcDateTime", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public Date getLastModifiedUtcDateTime() {
        return lastModifiedUtcDateTime;
    }

    public void setLastModifiedUtcDateTime(Date lastModifiedUtcDateTime) {
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
    }

    @XmlElement(name = "ExistsFromDate", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public Date getExistsFromDate() {
        return existsFromDate;
    }

    public void setExistsFromDate(Date existsFromDate) {
        this.existsFromDate = existsFromDate;
    }
}

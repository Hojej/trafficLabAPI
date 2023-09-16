package com.example.hojeij.TrafficlabAPI.Mappers;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement
public class BusDTO {
    private int lineNumber;
    private int directionCode;
    private String journeyPatternPointNumber;
    private Date lastModifiedUtcDateTime;
    private Date existsFromDate;

    public BusDTO(){

    }
    public BusDTO(int lineNumber, int directionCode, String journeyPatternPointNumber, Date lastModifiedUtcDateTime, Date existsFromDate) {
        this.lineNumber = lineNumber;
        this.directionCode = directionCode;
        this.journeyPatternPointNumber = journeyPatternPointNumber;
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
        this.existsFromDate = existsFromDate;
    }

    @XmlElement
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @XmlElement
    public int getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(int directionCode) {
        this.directionCode = directionCode;
    }

    @XmlElement
    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

    @XmlElement
    public Date getLastModifiedUtcDateTime() {
        return lastModifiedUtcDateTime;
    }

    public void setLastModifiedUtcDateTime(Date lastModifiedUtcDateTime) {
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
    }

    @XmlElement
    public Date getExistsFromDate() {
        return existsFromDate;
    }

    public void setExistsFromDate(Date existsFromDate) {
        this.existsFromDate = existsFromDate;
    }
}

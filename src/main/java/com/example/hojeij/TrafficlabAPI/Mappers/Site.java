package com.example.hojeij.TrafficlabAPI.Mappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.NoArgsConstructor;

@XmlType(name = "Site", propOrder = {
        "siteId",
        "siteName",
        "stopAreaNumber",
        "lastModifiedUtcDateTime",
        "existsFromDate"
})
@NoArgsConstructor
public class Site {
    private String siteId;
    private String siteName;
    private String stopAreaNumber;
    private String lastModifiedUtcDateTime;
    private String existsFromDate;

    @XmlElement(name = "SiteId", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @XmlElement(name = "SiteName", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @XmlElement(name = "StopAreaNumber", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getStopAreaNumber() {
        return stopAreaNumber;
    }

    public void setStopAreaNumber(String stopAreaNumber) {
        this.stopAreaNumber = stopAreaNumber;
    }

    @XmlElement(name = "LastModifiedUtcDateTime", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getLastModifiedUtcDateTime() {
        return lastModifiedUtcDateTime;
    }

    public void setLastModifiedUtcDateTime(String lastModifiedUtcDateTime) {
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
    }

    @XmlElement(name = "ExistsFromDate", namespace = Constants.JOURNEYPATTERNPOINTONLINE_NAMESPACE)
    public String getExistsFromDate() {
        return existsFromDate;
    }

    public void setExistsFromDate(String existsFromDate) {
        this.existsFromDate = existsFromDate;
    }
}

package com.example.hojeij.TrafficlabAPI.Common;

import com.example.hojeij.TrafficlabAPI.Mappers.BusXMLMapper;
import com.example.hojeij.TrafficlabAPI.Mappers.StationXMLMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class JAXBConverter {

    public static BusXMLMapper XMLtoBusPOJO(String res) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BusXMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (BusXMLMapper) unmarshaller.unmarshal(new StringReader(res));
    }

    public static StationXMLMapper XMLtoStationPOJO(String res) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(StationXMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (StationXMLMapper) unmarshaller.unmarshal(new StringReader(res));
    }
}

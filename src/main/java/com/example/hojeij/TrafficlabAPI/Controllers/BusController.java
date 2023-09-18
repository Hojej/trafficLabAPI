package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import com.example.hojeij.TrafficlabAPI.Mappers.XMLMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class BusController {

    private static final String _URI = "https://api.sl.se/api2/LineData.xml?model=jour&key=6e4cb358d1ea47b4b056a400d3b9a188&DefaultTransportModeCode=BUS";
    @GetMapping
    public void busInfo() throws URISyntaxException {
        HttpRequest get = HttpRequest.newBuilder()
                .uri(new URI(_URI))
                .header("key", "6e4cb358d1ea47b4b056a400d3b9a188")
                .header("model", "jour")
                .header("DefaultTransportModeCode", "BUS")
                .GET().build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse res = null;
        try {
            res = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(res.body());
        List<JourneyPatternPointOnLine> list;
        try {
            list = XMLtoString(res.body().toString()).getResponseData().getList();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < list.size(); i++) {
            JourneyPatternPointOnLine temp = list.get(i);
            System.out.println("Number: " + temp.getLineNumber() + " DirectionNumber: " + temp.getDirectionCode() + " Point: " + temp.getJourneyPatternPointNumber());
        }
    }

    private static XMLMapper XMLtoString(String res) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (XMLMapper) unmarshaller.unmarshal(new StringReader(res));

        //return (XMLMapper) unmarshaller.unmarshal(new StringReader(res));
    }
}

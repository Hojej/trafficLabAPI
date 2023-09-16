package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.BusDTO;
import com.example.hojeij.TrafficlabAPI.Mappers.XMLMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
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

    private static final String _URI = "https://api.sl.se/api2/LineData.xml?model=jour&key=5da196d47f8f4e5facdb68d2e25b9eae&DefaultTransportModeCode=BUS";
    @GetMapping
    public void busInfo() throws URISyntaxException {
        HttpRequest get = HttpRequest.newBuilder()
                .uri(new URI(_URI))
                .header("key", "5da196d47f8f4e5facdb68d2e25b9eae")
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

        try {
            System.out.println(XMLtoString(res.body().toString()).get(0).getLineNumber());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BusDTO> XMLtoString(String res) throws JAXBException {
        /*res =
                "    <ResponseData>\n" +
                "        <Version xmlns=\"http://api.sl.se/api/pws\">2023-09-13 00:10</Version>\n" +
                "        <Type xmlns=\"http://api.sl.se/api/pws\">JourneyPatternPointOnLine</Type>\n" +
                "        <JourneyPatternPointOnLine xmlns=\"http://api.sl.se/api/pws\">\n" +
                "            <LineNumber>1</LineNumber>\n" +
                "            <DirectionCode>1</DirectionCode>\n" +
                "            <JourneyPatternPointNumber>10008</JourneyPatternPointNumber>\n" +
                "            <LastModifiedUtcDateTime>2022-02-15 00:00:00.000</LastModifiedUtcDateTime>\n" +
                "            <ExistsFromDate>2022-02-15 00:00:00.000</ExistsFromDate>\n" +
                "        </JourneyPatternPointOnLine>\n";*/
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (List<BusDTO>) unmarshaller.unmarshal(new StringReader(res));
    }
}

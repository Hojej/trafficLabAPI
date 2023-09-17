package com.example.hojeij.TrafficlabAPI.Controllers;

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

        try {
            System.out.println(XMLtoString(res.body().toString()).getResponseData().getVersion());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static XMLMapper XMLtoString(String res) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLMapper mapper = (XMLMapper) unmarshaller.unmarshal(new StringReader(res));

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "C:\\Users\\Hojeij\\IdeaProjects\\trafficLabAPI\\src\\main\\resources\\static\\data-test.xsd");
        marshaller.marshal(mapper, System.out);

        return (XMLMapper) unmarshaller.unmarshal(new StringReader(res));
    }
}

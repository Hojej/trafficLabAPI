package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Common.JAXBConverter;
import com.example.hojeij.TrafficlabAPI.Mappers.Constants;
import com.example.hojeij.TrafficlabAPI.Mappers.Site;
import jakarta.xml.bind.JAXBException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusStopController {

    public Map<String, String> stopsInfo() throws URISyntaxException {

        return dataFormatter(createBusStopNamesList(callAPIToGetStationNames()));
    }

    private Map<String, String> dataFormatter(List<Site> list) {
        Map<String, String> toRet = new HashMap<>();
        for (int i = 0; i < list.size(); i++){
            toRet.put(list.get(i).getStopAreaNumber(), list.get(i).getSiteName());
        }
        return toRet;
    }

    private List<Site> createBusStopNamesList(HttpResponse res){
        List<Site> list;
        try {
            list = JAXBConverter.XMLtoStationPOJO(res.body().toString()).getResponseData().getList();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private HttpResponse callAPIToGetStationNames() throws URISyntaxException {
        HttpRequest get = HttpRequest.newBuilder()
                .uri(new URI(Constants.BUSSTOP_URI))
                .GET().build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse res = null;
        try {
            res = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}

package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Common.JAXBConverter;
import com.example.hojeij.TrafficlabAPI.Mappers.Constants;
import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import jakarta.xml.bind.JAXBException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@RestController
public class BusController {

    @GetMapping
    public void busInfo() throws URISyntaxException {

        HttpResponse busResponse = callAPIToGetBuses();

        Map<Integer, List<JourneyPatternPointOnLine>> sortedListofTop10Buses = sortedMap(formatterMap(createBusStopIDList(busResponse)));

        BusStopController busStopController = new BusStopController();

        printResult(sortedListofTop10Buses, busStopController.stopsInfo());
    }

    private List<JourneyPatternPointOnLine> createBusStopIDList(HttpResponse res){
        List<JourneyPatternPointOnLine> list;
        try {
            list = JAXBConverter.XMLtoBusPOJO(res.body().toString()).getResponseData().getList();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private HttpResponse callAPIToGetBuses() throws URISyntaxException {
        HttpRequest get = HttpRequest.newBuilder()
                .uri(new URI(Constants.BUSES_URI))
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

    private void printResult(Map<Integer, List<JourneyPatternPointOnLine>> finish, Map<String, String> stops){
        System.out.println(finish.keySet());
        ArrayList<Integer> arr = new ArrayList<>(finish.keySet());
        for (int i = 0; i < finish.size(); i++) {
            List<JourneyPatternPointOnLine> value = finish.get(arr.get(i));
            for (int j = 0; j < value.size(); j++) {
                if (stops.containsKey(value.get(j).getJourneyPatternPointNumber())) {
                    System.out.println("Linje " + arr.get(i) + ": Hållplats (" + j + "): " + stops.get(value.get(j).getJourneyPatternPointNumber()));
                } else {
                    System.out.println("Linje " + arr.get(i) + ": Hållplats (" + j + "): " + value.get(j).getJourneyPatternPointNumber());
                }
            }
        }
    }

    private HashMap<Integer, List<JourneyPatternPointOnLine>> formatterMap(List<JourneyPatternPointOnLine> list){
        HashMap<Integer, List<JourneyPatternPointOnLine>> toRet = new HashMap<>();
        int busLine = 0;
        while(!list.isEmpty()){
            List<JourneyPatternPointOnLine> tempList = new ArrayList<>();
            tempList.add(list.get(list.size()-1));
            busLine = list.get(list.size()-1).getLineNumber();
            list.remove(list.size()-1);
            for (int i = list.size()-1; i >= 0; i--){
                if(list.get(i).getLineNumber() == tempList.get(0).getLineNumber()){
                    tempList.add(list.get(i));
                    list.remove(i);
                }
            }
            toRet.put(busLine, tempList);
        }
        return toRet;
    }

    private Map<Integer, List<JourneyPatternPointOnLine>> sortedMap(Map<Integer, List<JourneyPatternPointOnLine>> toSortMap){
        LinkedList<Map.Entry<Integer, List<JourneyPatternPointOnLine>>> toComapareList =
                new LinkedList<Map.Entry<Integer, List<JourneyPatternPointOnLine>>>(toSortMap.entrySet());
        Comparator<Map.Entry<Integer, List<JourneyPatternPointOnLine>>> comparator = new Comparator<Map.Entry<Integer, List<JourneyPatternPointOnLine>>>() {

            @Override
            public int compare(Map.Entry<Integer, List<JourneyPatternPointOnLine>> o1, Map.Entry<Integer, List<JourneyPatternPointOnLine>> o2) {
                if(o1.getValue().size() < o2.getValue().size()){
                    return 1;
                } else if (o1.getValue().size() > o2.getValue().size()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }

        };
        Collections.sort(toComapareList, comparator);
        HashMap<Integer, List<JourneyPatternPointOnLine>> toRet = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            toRet.put(toComapareList.get(i).getKey(),toComapareList.get(i).getValue());
        }
        return toRet;
    }
}
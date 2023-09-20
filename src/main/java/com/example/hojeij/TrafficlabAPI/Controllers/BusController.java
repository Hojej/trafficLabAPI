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
import java.util.*;

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
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<JourneyPatternPointOnLine> list;
        try {
            list = XMLtoString(res.body().toString()).getResponseData().getList();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        Map<Integer, List<JourneyPatternPointOnLine>> doublelist= formatterMap(list);

        List<Integer> points = new ArrayList<>(doublelist.keySet());

        Map<Integer, Integer> busStopsMap = new HashMap<>();
        for (int i = 0; i < doublelist.size(); i++){
            busStopsMap.put(points.get(i), doublelist.get(points.get(i)).size());
        }

        Map<Integer, List<JourneyPatternPointOnLine>> finish = sortedMap(doublelist);

        System.out.println(finish.keySet());
        ArrayList<Integer> arr = new ArrayList<>(finish.keySet());
        for (int i = 0; i < finish.size(); i++){
            for (int j = 0; j < finish.get(arr.get(i)).size(); j++)
            System.out.println("Linje " + arr.get(i) + ": Hållplats ("+ j +"): " + finish.get(arr.get(i)).get(j).getJourneyPatternPointNumber());
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

    private static XMLMapper XMLtoString(String res) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLMapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (XMLMapper) unmarshaller.unmarshal(new StringReader(res));
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

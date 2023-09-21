package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.BusXMLMapper;
import com.example.hojeij.TrafficlabAPI.Mappers.Constants;
import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import com.example.hojeij.TrafficlabAPI.Mappers.StationXMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
public class BusController {

    @Autowired
    private RestTemplate restTemplate; //Could use WebClient instead

    @GetMapping
    public void busInfo() {

        Map<Integer, List<JourneyPatternPointOnLine>> sortedListofTop10Buses = sortedMap(formatterMap(getCallBusses().getResponseData().getList()));

        BusStopController busStopController = new BusStopController();

        printResult(sortedListofTop10Buses, busStopController.stopsInfo(getCallStations().getResponseData().getList()));
    }

    private BusXMLMapper getCallBusses(){
        return restTemplate.getForObject(Constants.BUSES_URI, BusXMLMapper.class);
    }

    private StationXMLMapper getCallStations(){
        return restTemplate.getForObject(Constants.BUSSTOP_URI, StationXMLMapper.class);
    }

    private void printResult(Map<Integer, List<JourneyPatternPointOnLine>> finish, Map<String, String> stops){
        System.out.println(finish.keySet());
        ArrayList<Integer> arr = new ArrayList<>(finish.keySet());
        String stopName;
        for (int i = 0; i < finish.size(); i++) {
            List<JourneyPatternPointOnLine> value = finish.get(arr.get(i));
            for (int j = 0; j < value.size(); j++) {
                stopName = stops.containsKey(value.get(j).getJourneyPatternPointNumber())
                        ? stops.get(value.get(j).getJourneyPatternPointNumber())
                        : value.get(j).getJourneyPatternPointNumber();
                System.out.println("Linje " + arr.get(i) + ": Hållplats (" + j + "): " + stopName);
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
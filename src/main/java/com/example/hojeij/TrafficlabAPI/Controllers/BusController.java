package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.BusXMLMapper;
import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import com.example.hojeij.TrafficlabAPI.Mappers.StationXMLMapper;
import com.example.hojeij.TrafficlabAPI.Utility.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private WebClient.Builder getWebClientBuilder;
    private UtilityMethods utility;

    @GetMapping("/top-10-busiest-buslines")
    public void busInfo() {

        utility = new UtilityMethods();

        BusXMLMapper busXMLMapper = utility.getCallBussesWeb(getWebClientBuilder);

        StationXMLMapper stationXMLMapper = utility.getCallStationsWeb(getWebClientBuilder);

        Map<String, String>  stations = utility.stopsInfo(stationXMLMapper.getResponseData().getList());

        Map<Integer, List<JourneyPatternPointOnLine>> busLines = utility.formatterMap(busXMLMapper.getResponseData().getList(), stations);

        if(Objects.nonNull(busXMLMapper)){
            if(Objects.nonNull(stationXMLMapper)){
                Map<Integer, List<JourneyPatternPointOnLine>> sortedListofTop10Buses = utility.sortedMap(busLines);

                utility.printResult(sortedListofTop10Buses, stations);
            } else {
                System.out.printf("Station names couldn't be loaded!");
            }

        }else {
            throw new UnknownFormatConversionException("Response could not be formatted correctly!");
        }
    }

    /*private HashMap<Integer, List<JourneyPatternPointOnLine>> filterStops(Map<Integer, List<JourneyPatternPointOnLine>> busLines,
                                                                          Map<String, String>  stations){
        ArrayList<String> busStopIDList = new ArrayList<>(stations.keySet());
        ArrayList<Integer> busLinesList = new ArrayList<>(busLines.keySet());
        Map<Integer, List<JourneyPatternPointOnLine>> toRet = new HashMap<>();

        for (int i = 0; i < busLinesList.size(); i++){
            for (int j = 0; j < busStopIDList.size(); j++){
                if(){
                    for (int j = 0; j < busStopIDList.size(); j++){
                        if(stations.containsKey(busLines.get(busLinesList.get(i)).get(j).getJourneyPatternPointNumber()){

                        }
                    }
                }
            }
        }
    }*/
}
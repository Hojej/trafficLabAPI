package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.BusXMLMapper;
import com.example.hojeij.TrafficlabAPI.Mappers.Constants;
import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import com.example.hojeij.TrafficlabAPI.Mappers.StationXMLMapper;
import com.example.hojeij.TrafficlabAPI.Utility.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private RestTemplate restTemplate; //Could use WebClient instead

    @GetMapping("/top-10-busiest-buslines")
    public void busInfo() {

        UtilityMethods utility = new UtilityMethods();

        BusXMLMapper busXMLMapper = getCallBusses();

        if(Objects.nonNull(busXMLMapper)){
            Map<Integer, List<JourneyPatternPointOnLine>> sortedListofTop10Buses = utility.sortedMap(utility.formatterMap(busXMLMapper.getResponseData().getList()));
            BusStopController busStopController = new BusStopController();

            utility.printResult(sortedListofTop10Buses, busStopController.stopsInfo(getCallStations().getResponseData().getList()));
        }else {
            throw new UnknownFormatConversionException("Response could not be formatted correctly!");
        }
    }

    private BusXMLMapper getCallBusses(){
        return restTemplate.getForObject(Constants.BUSES_URI, BusXMLMapper.class);
    }

    private StationXMLMapper getCallStations(){
        if(restTemplate == null)
            return null;
        return restTemplate.getForObject(Constants.BUSSTOP_URI, StationXMLMapper.class);
    }
}
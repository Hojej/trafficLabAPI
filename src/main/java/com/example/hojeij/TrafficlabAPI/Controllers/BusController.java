package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.BusXMLMapper;
import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
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
    private UtilityMethods utility;

    @GetMapping("/top-10-busiest-buslines")
    public void busInfo() {

        utility = new UtilityMethods();

        BusXMLMapper busXMLMapper = utility.getCallBusses(restTemplate);

        if(Objects.nonNull(busXMLMapper)){
            Map<Integer, List<JourneyPatternPointOnLine>> sortedListofTop10Buses = utility.sortedMap(utility.formatterMap(busXMLMapper.getResponseData().getList()));

            utility.printResult(sortedListofTop10Buses, utility.stopsInfo(utility.getCallStations(restTemplate).getResponseData().getList()));
        }else {
            throw new UnknownFormatConversionException("Response could not be formatted correctly!");
        }
    }
}
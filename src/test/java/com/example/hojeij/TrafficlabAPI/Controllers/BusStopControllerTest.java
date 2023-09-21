package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.Site;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BusStopControllerTest {

    @InjectMocks
    private BusStopController busStopController;

    @Test
    void stopsInfo() {
        List<Site> list = new ArrayList<>();
        list.add(new Site("1", "Anna Linds Plats", "1", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add(new Site("2", "Djäknegatan", "2", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add(new Site("3", "Orkanen", "3", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        Map<String, String> listToCompare = busStopController.stopsInfo(list);
        Map<String, String> comparedTo = new HashMap<>();
        comparedTo.put("1", "Anna Linds Plats");
        comparedTo.put("2", "Djäknegatan");
        comparedTo.put("3", "Orkanen");
        assertEquals(listToCompare, comparedTo);
    }

    @Test
    void stopsInfoWithNull(){
        List<Site> list = new ArrayList<>();
        Map<String, String> listToCompare = busStopController.stopsInfo(list);
        Map<String, String> comparedTo = new HashMap<>();
        assertEquals(listToCompare, comparedTo);
    }
}
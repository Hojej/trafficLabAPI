package com.example.hojeij.TrafficlabAPI.Utility;

import com.example.hojeij.TrafficlabAPI.Mappers.JourneyPatternPointOnLine;
import com.example.hojeij.TrafficlabAPI.Mappers.Site;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UtilityMethodsTest {

    @InjectMocks
    private UtilityMethods utilityMethods;

    /*@Test
    void formatterMap() {
        Map<Integer, List<JourneyPatternPointOnLine>> mapToCompare = new HashMap<>();
        List<JourneyPatternPointOnLine> list = new ArrayList<>();
        list.add( new JourneyPatternPointOnLine(1, 1, "1337", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add( new JourneyPatternPointOnLine(1, 1, "1338", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add( new JourneyPatternPointOnLine(2, 1, "1339", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));

        List<JourneyPatternPointOnLine> line1List = new ArrayList<>();
        line1List.add(new JourneyPatternPointOnLine(1, 1, "1337", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        line1List.add(new JourneyPatternPointOnLine(1, 1, "1338", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        List<JourneyPatternPointOnLine> line2List = new ArrayList<>();
        line2List.add(new JourneyPatternPointOnLine(2, 1, "1339", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));

        mapToCompare.put(1, line1List);
        mapToCompare.put(2, line2List);

        assertEquals(utilityMethods.formatterMap(list).size(), mapToCompare.size());
    }

    @Test
    void sortedMap() {
        Map<Integer, List<JourneyPatternPointOnLine>> mapToCompare = new HashMap<>();
        List<JourneyPatternPointOnLine> list = new ArrayList<>();
        list.add( new JourneyPatternPointOnLine(1, 1, "1337", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add( new JourneyPatternPointOnLine(1, 1, "1338", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add( new JourneyPatternPointOnLine(2, 1, "1339", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add( new JourneyPatternPointOnLine(1, 1, "1340", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));

        List<JourneyPatternPointOnLine> line1List = new ArrayList<>();
        line1List.add(new JourneyPatternPointOnLine(1, 1, "1337", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        line1List.add(new JourneyPatternPointOnLine(1, 1, "1338", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        line1List.add( new JourneyPatternPointOnLine(1, 1, "1340", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        List<JourneyPatternPointOnLine> line2List = new ArrayList<>();
        line2List.add(new JourneyPatternPointOnLine(2, 1, "1339", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));

        mapToCompare.put(1, line1List);
        mapToCompare.put(2, line2List);

        Map<Integer, List<JourneyPatternPointOnLine>> mapForCompare = utilityMethods.formatterMap(list);

        assertEquals(utilityMethods.sortedMap(mapForCompare).keySet(), mapToCompare.keySet());
    }*/

    @Test
    void stopsInfo() {
        List<Site> list = new ArrayList<>();
        list.add(new Site("1", "Anna Linds Plats", "1", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add(new Site("2", "Djäknegatan", "2", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        list.add(new Site("3", "Orkanen", "3", "2011-10-1 00:00:00.000", "2011-10-1 00:00:00.000"));
        Map<String, String> listToCompare = utilityMethods.stopsInfo(list);
        Map<String, String> comparedTo = new HashMap<>();
        comparedTo.put("1", "Anna Linds Plats");
        comparedTo.put("2", "Djäknegatan");
        comparedTo.put("3", "Orkanen");
        assertEquals(listToCompare, comparedTo);
    }

    @Test
    void stopsInfoWithNull(){
        List<Site> list = new ArrayList<>();
        Map<String, String> listToCompare = utilityMethods.stopsInfo(list);
        Map<String, String> comparedTo = new HashMap<>();
        comparedTo.put("-1", "-1");
        assertEquals(listToCompare, comparedTo);
    }
}
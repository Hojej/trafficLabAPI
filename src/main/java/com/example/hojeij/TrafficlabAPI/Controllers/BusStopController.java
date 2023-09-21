package com.example.hojeij.TrafficlabAPI.Controllers;

import com.example.hojeij.TrafficlabAPI.Mappers.Site;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusStopController {

    public Map<String, String> stopsInfo(List<Site> list){
        return dataFormatter(list);
    }

    private Map<String, String> dataFormatter(List<Site> list) {
        Map<String, String> toRet = new HashMap<>();
        if(!list.isEmpty()){
            for (int i = 0; i < list.size(); i++){
                toRet.put(list.get(i).getStopAreaNumber(), list.get(i).getSiteName());
            }
            return toRet;
        } else {
            toRet.put("-1", "-1");
            return toRet;
        }
    }
}

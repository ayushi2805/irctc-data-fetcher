package com.ayushi.traininfo.controller;

import com.ayushi.traininfo.model.Station;
import com.ayushi.traininfo.service.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("stations")
public class StationsController {
    @Autowired
    private StationServiceImpl stationService;
    @GetMapping()
    public String getAllStationsStartingWith(@RequestParam("q") String stationQuery,
                                             @RequestParam(required = false) String stationLimit,
                                             Model model) throws IOException, InterruptedException {
        List<Station> stations = (List<Station>)stationService.getStationStartingWith(stationQuery, stationLimit);
        model.addAttribute("stations", stations);
        return "stations/getStationsStartingWith";
    }
    @GetMapping({"/allStations", "allStations"})
//    @ResponseBody
    public String getAllStations(Model model) throws IOException, InterruptedException {
        List<List<Station>> allStations = (List<List<Station>>)stationService.getAllStations();
        model.addAttribute("allStations", allStations);
        return "stations/getAllStations";
    }
    @GetMapping("/updateStations")
    public String updateAllStations(Model model) throws IOException, InterruptedException {
        List<List<Station>> allStations = (List<List<Station>>)stationService.updateAllStations();
        model.addAttribute("allStations", allStations);
        return "stations/updateStations";
    }
}

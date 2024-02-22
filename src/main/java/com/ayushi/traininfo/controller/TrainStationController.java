package com.ayushi.traininfo.controller;

import com.ayushi.traininfo.model.StationCode;
import com.ayushi.traininfo.model.TrainStation;
import com.ayushi.traininfo.service.TrainStationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("trainsBetweenStations")
public class TrainStationController {
    @Autowired
    private TrainStationServiceImpl trainStationService;

    @GetMapping
    public String getTrainsBetweenStations(@RequestParam StationCode fromStation,
                                           @RequestParam StationCode toStation,
                                           @RequestParam(required = false) boolean allTrains,
                                           Model model) throws IOException, InterruptedException {
        List<TrainStation> trains = trainStationService.getTrainsBetweenStations(
                fromStation, toStation, allTrains);
        model.addAttribute("trains", trains);
        return "trainStation/trains-between-stations";
    }
}

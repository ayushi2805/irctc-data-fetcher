package com.ayushi.controller;

import com.ayushi.model.Station;
import com.ayushi.service.StationService;
import com.ayushi.service.StationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.ayushi.IrctcDataFetcherApplication.appRoot;

@RestController
@RequestMapping("stations")
public class StationsController {
    @Autowired
    private StationServiceImpl stationService;
    @GetMapping()
    public Object getAllStationsStartingWith(@RequestParam("q") String stationQuery,
                                             @RequestParam(required = false) String stationLimit) throws IOException, InterruptedException {
        return stationService.getStationStartingWith(stationQuery, stationLimit);
    }
    @GetMapping("/allStations")
    public Object getAllStations() throws IOException, InterruptedException {
        return stationService.getAllStations();
    }
    @GetMapping("/updateStations")
    public Object updateAllStations() throws IOException, InterruptedException {
        return stationService.updateAllStations();
    }
}

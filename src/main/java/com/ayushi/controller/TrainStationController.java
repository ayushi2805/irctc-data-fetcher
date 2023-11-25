package com.ayushi.controller;

import com.ayushi.model.StationCode;
import com.ayushi.model.TrainClass;
import com.ayushi.model.TrainStation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
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
@RequestMapping("trainsBetweenStations")
public class TrainStationController {
    @GetMapping
    public Object getTrainsBetweenStations(@RequestParam StationCode fromStation,
                                           @RequestParam StationCode toStation,
                                           @RequestParam(required = false) boolean allTrains) throws IOException, InterruptedException {

        List<TrainStation> trainInfo = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = appRoot + "v1/trainsBtwStations?fromStation=" +
                fromStation + "&toStation=" +
                toStation + "&allTrains=" + allTrains;
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONArray data = (JSONArray) new JSONObject(response.body()).get("data");
            for(Object object : data) {
                trainInfo.add(objectMapper.readValue(object.toString(), TrainStation.class));
            }
        }
        return trainInfo;
    }
}

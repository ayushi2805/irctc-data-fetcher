package com.ayushi.traininfo.service;

import com.ayushi.traininfo.model.StationCode;
import com.ayushi.traininfo.model.TrainStation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.ayushi.traininfo.TrainInfoApplication.appRoot;

@Service
public class TrainStationServiceImpl implements TrainStationService{
    @Override
    public List<TrainStation> getTrainsBetweenStations(StationCode fromStation,
                                                       StationCode toStation,
                                                       boolean allTrains) throws IOException, InterruptedException {
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

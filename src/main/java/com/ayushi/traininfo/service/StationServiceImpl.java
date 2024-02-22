package com.ayushi.traininfo.service;

import com.ayushi.traininfo.model.Station;
import com.ayushi.traininfo.respository.StationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StationServiceImpl implements StationService{
    Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);
    @Autowired
    private StationRepository stationRepository;
    int counter = 0;
    @Override
    public Object getStationStartingWith(String stationQuery,
                                          String stationLimit) throws IOException, InterruptedException {

        List<Station> stations = null;
        try {
            stations = stationRepository.findStationsStartingWith(stationQuery);
            if (stations.size() == 0) {
                stations = fetchStationsFromRailway(stationQuery, stationLimit);
            }
        } catch(Exception exception) {
            System.out.println("Unable to get station list");
        }
        return stations;
    }
    public List<Station> fetchStationsFromRailway(String stationQuery,
                                                  String stationLimit) throws IOException, InterruptedException {
        List<Station>  stations = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = appRoot + "v1/stations?q=" + stationQuery + "&limit=" + stationLimit;
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            JSONArray jsonArray = (JSONArray) new JSONObject(response.body()).get("data");
            for (Object object : jsonArray) {
                ObjectMapper objectMapper = new ObjectMapper();
                Station station = objectMapper.readValue(object.toString(), Station.class);
                if(station.getId()!=null
                        && station.getStationCode()!=null
                        && station.getStationName()!=null
                ) {
                    logger.info("proceeding to save station " + ": " + station.getStationName());
                    Station savedStation = processStationInformation(station);
                    stations.add(savedStation);
                }
            }
        }
        return stations;
    }
    //@Cacheable(cacheNames = "stations", key = "#station.stationCode")
    public Station processStationInformation(Station station) {
        Station existingStation = stationRepository.findStationByStationCode(station.getStationCode());
        Station savedStation = null;
        if(existingStation==null){
            savedStation = stationRepository.save(station);
            logger.info("station saved: " + savedStation.getStationName());
        }else{
            logger.info("Station already present: " + station.getStationName());
            ++counter;
        }
        return savedStation;
    }
    @Override
    public Object getAllStations() throws IOException, InterruptedException {
        List<List<Station>> allStations = new ArrayList<>();
        for(char ch='a'; ch<='z'; ch++) {
            logger.info("Getting Stations for letter " +  ch);
            allStations.add((List<Station>) getStationStartingWith(Character.toString(ch), "100"));
        }
        return allStations;
    }
    @Override
    public Object updateAllStations() throws IOException, InterruptedException {
        List<List<Station>> allStations = new ArrayList<>();
        for(char ch='a'; ch<='z'; ch++) {
            logger.info("Getting Stations for letter " +  ch);
            List<Station> stationList = fetchStationsFromRailway(Character.toString(ch), "100");
            if(stationList!=null && stationList.size()!=0) {
                allStations.add(stationList);
            }
        }
        logger.info("Total stations: " + counter);
        for (List<Station> station: allStations) {
            System.out.println(station);
        }
        return allStations;
    }
}

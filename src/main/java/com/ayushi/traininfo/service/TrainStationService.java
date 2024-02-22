package com.ayushi.traininfo.service;

import com.ayushi.traininfo.model.StationCode;
import com.ayushi.traininfo.model.TrainStation;

import java.io.IOException;
import java.util.List;

public interface TrainStationService {
    public List<TrainStation> getTrainsBetweenStations(StationCode fromStation,
                                                       StationCode toStation,
                                                       boolean allTrains) throws IOException, InterruptedException;
}

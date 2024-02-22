package com.ayushi.traininfo.service;

import java.io.IOException;

public interface StationService {
    public Object getAllStations() throws IOException, InterruptedException;
    public Object updateAllStations() throws IOException, InterruptedException;
    public Object getStationStartingWith(String stationQuery,
                                         String stationLimit) throws IOException, InterruptedException;
}

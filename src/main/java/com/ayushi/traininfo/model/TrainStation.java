package com.ayushi.traininfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainStation {
    private String trainNumber;
    private String trainName;
    private String trainFullName;
    private Map<String, Boolean> trainRunsOn;
    private List<String> availableClasses;
    private Boolean hasPantry;
    private String trainType;
    private String returnTrainNumber;
    private FromToStationDetails stationFrom;
    private FromToStationDetails stationTo;
    private String updatedAt;
    private int distance;
    private String duration;
}

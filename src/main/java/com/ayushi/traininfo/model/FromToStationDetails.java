package com.ayushi.traininfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FromToStationDetails {
    private int srN;
    private String arrivalTime;
    private String departureTime;
    private String haltTime;
    private String platform;
    private int dayCount;
    private String distance;
    private String speed;
    private int boardingDisabled;
    private String updatedAt;
    private String stationCode;
    private String stationName;
}

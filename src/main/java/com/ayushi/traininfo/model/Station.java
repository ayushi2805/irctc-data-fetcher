package com.ayushi.traininfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
//    @Indexed
    private String stationCode;
    private String stationName;

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}

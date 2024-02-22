package com.ayushi.traininfo.service;

import com.ayushi.traininfo.model.Train;

import java.io.IOException;
import java.util.List;

public interface TrainService {
    public List<Train> getTrainsWithPrefix(String query, String limit) throws IOException, InterruptedException;
}

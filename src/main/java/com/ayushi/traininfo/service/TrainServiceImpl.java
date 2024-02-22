package com.ayushi.traininfo.service;

import com.ayushi.traininfo.model.Train;
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
public class TrainServiceImpl implements TrainService{
    @Override
    public List<Train> getTrainsWithPrefix(String query, String limit) throws IOException, InterruptedException {
        List<Train> trains = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = appRoot + "v1/trains?q=" + query + "&limit=" + limit;
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url))
                .header("accept",
                        "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200) {
            JSONArray jsonArray = (JSONArray) new JSONObject(response.body()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            for(Object object: jsonArray) {
                trains.add(objectMapper.readValue(object.toString(), Train.class));
            }
        } else {
            //implement error handling everywhere to accommodate !200 status codes
        }
        return trains;
    }
}

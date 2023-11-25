package com.ayushi.controller;

import com.ayushi.model.Train;
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
@RequestMapping("trains")
public class TrainsController {
    //get info of all trains starting with a train number
    // apply min, max validation in limit
    //@Size(min = 10, max = 100, message = "train limit should be between 10 and 100")
    @GetMapping()
    public Object getAllTrainsStartingWith(@RequestParam("q") String trainQuery,
                                           @RequestParam(required = false) String trainLimit) throws IOException, InterruptedException {
        List<Train> trains = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = appRoot + "v1/trains?q=" + trainQuery + "&limit=" + trainLimit;
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

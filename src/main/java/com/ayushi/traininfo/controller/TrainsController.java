package com.ayushi.traininfo.controller;

import com.ayushi.traininfo.model.Train;
import com.ayushi.traininfo.service.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("trains")
public class TrainsController {
    @Autowired
    private TrainServiceImpl trainService;

    //get info of all trains starting with a train number
    // apply min, max validation in limit
    //@Size(min = 10, max = 100, message = "train limit should be between 10 and 100")
    @GetMapping()
    public String getAllTrainsStartingWith(@RequestParam("q") String trainQuery,
                                           @RequestParam(required = false) String trainLimit,
                                           Model model) throws IOException, InterruptedException {
        List<Train> trains = trainService.getTrainsWithPrefix(trainQuery, trainLimit);
        model.addAttribute("trainList", trains);
        return "trains/index.html";
    }
}

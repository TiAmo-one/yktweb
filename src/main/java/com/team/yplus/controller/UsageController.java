package com.team.yplus.controller;

import com.team.yplus.response.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsageController {
    @GetMapping("/usage/summary")
    public RestResult UsageSummary(){

        return RestResult.succ(1);
    }
    @GetMapping("/usage/summary/{unit_name}")
    public RestResult UsageSummary(@PathVariable("unit_name")String unitName){

        return RestResult.succ(1);
    }

}

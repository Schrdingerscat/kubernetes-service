package org.example.controller;

import org.example.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("app")
public class AppController {
    @Autowired
    private AppService appService;

    @GetMapping("/getIPAndPort")
    public ResponseEntity getPortAndIPByAppID(@RequestParam String appID){
        Map<String,String> result = appService.getPortAndIPByAppID(appID);
        if(result.size()==0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}

package org.example.controller;

import org.example.service.NodeIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("NodeIP")
public class NodeIPController {

    @Autowired
    private NodeIPService nodeIPService;

    @GetMapping("/")
    public ResponseEntity getIPByNodeName(@RequestParam String nodeName){

        String result = nodeIPService.getIPByNodeName(nodeName);
        if(result==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
}

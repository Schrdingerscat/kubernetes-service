package org.example.service;

import org.example.mapper.NodeIPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeIPService {
    @Autowired
    private NodeIPMapper nodeIPMapper;

    public String getIPByNodeName(String nodeName){
        if(nodeName!=null){
            return nodeIPMapper.getIPByNodeName(nodeName);
        }else{
            return null;
        }
    }

}

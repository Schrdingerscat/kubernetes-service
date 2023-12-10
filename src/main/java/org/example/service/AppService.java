package org.example.service;

import org.example.entity.ResultVO;
import org.example.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

@Service
public class AppService {
    @Autowired
    public AppMapper appMapper;

    @Autowired
    public NodeIPService nodeIPService;
    public Map<String,String> getPortAndIPByAppID(String appID){
        // 创建Random对象
        Random random = new Random();
        // 生成一个随机整数
        int randomNumber = random.nextInt();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http:/localhost:8080/namespace/nodeName";
        Map<String,String > request = new TreeMap<>();
        request.put("namespace",appID);
        ResponseEntity<ResultVO> response = restTemplate.getForEntity(url, ResultVO.class,request);
        // 获取响应结果
        ResultVO responseResult = response.getBody();
        Set<String> nodeNames = (Set<String>) responseResult.data;
        int sz = nodeNames.size();
        String[] nameArray = nodeNames.toArray(new String[sz]);
        String targetNodeName = nameArray[randomNumber%sz];
        Map<String,String> result = new TreeMap<String, String>();

        String IP = nodeIPService.getIPByNodeName(targetNodeName);

        Integer port = appMapper.getPortByAppID(appID);

        result.put("IP",IP);
        result.put("port",port.toString());
        return result;
    }

}

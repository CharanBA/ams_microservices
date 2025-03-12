package com.optimas.relationshipservice.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "component-service", url = "http://localhost:8762")
public interface ComponentServiceClient {
    
    @GetMapping("/components")
    ResponseEntity<String> getComponentById(@RequestParam("id") String componentId);
    
    @GetMapping("/components/asset")
    ResponseEntity<List<Object>> getComponentsByAssetId(@RequestParam("id") String assetId); 

}



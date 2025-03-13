package com.optimas.relationshipservice.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.optimas.relationshipservice.dto.ApiResponse;
import com.optimas.relationshipservice.dto.ComponentDTO;

@FeignClient(name = "component-service", url = "http://localhost:8762")
public interface ComponentServiceClient {
    
    @GetMapping("/components")
    ResponseEntity<String> getComponentById(@RequestParam("id") String componentId);
    
    @GetMapping("/components/by-asset")
    ResponseEntity<ApiResponse<List<ComponentDTO>>> getComponentsByAssetId(@RequestParam("id") String assetId); 

}



package com.optimas.relationshipservice.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.optimas.relationshipservice.dto.ApiResponse;
import com.optimas.relationshipservice.dto.ComponentDTO;

@FeignClient(name = "AssetService", url = "http://localhost:8081")
public interface AssetServiceClient {
    
    @GetMapping("/assets")
    ResponseEntity<String> getAssetById(@RequestParam("id") String assetId);
    
    @GetMapping("/assets/components")
    ResponseEntity<ApiResponse<List<ComponentDTO>>> getComponentsByAssetId(@RequestParam("id") String assetId); 
}

package com.optimas.relationshipservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AssetService", url = "http://localhost:8081")
public interface AssetServiceClient {
    
    @GetMapping("/assets")
    ResponseEntity<String> getAssetById(@RequestParam("id") String assetId);
}

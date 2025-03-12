package com.optimas.componentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.optimas.componentservice.dto.AssetDTO;

@FeignClient(name = "AssetService", url = "http://localhost:8081")
public interface AssetServiceClient {
    
    @GetMapping("/assets")
    AssetDTO getAssetById(@RequestParam("id") String assetId);
}

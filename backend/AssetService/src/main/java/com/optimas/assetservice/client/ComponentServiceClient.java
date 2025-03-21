package com.optimas.assetservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.optimas.assetservice.dto.ComponentDTO;
import com.optimas.assetservice.dto.ApiResponse;

import java.util.List;

@FeignClient(name = "COMPONENTSERVICE", url = "http://localhost:8762/components")
public interface ComponentServiceClient {

    @GetMapping("/by-asset")
    ResponseEntity<ApiResponse<List<ComponentDTO>>> getComponentsByAssetId(@RequestParam("id") String assetId); 
}


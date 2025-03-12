package com.optimas.assetservice.controller;

import com.optimas.assetservice.dto.ApiResponse;
import com.optimas.assetservice.model.AssetDef;
import com.optimas.assetservice.service.AssetService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addAsset(@RequestBody AssetDef asset) {
        String assetId = assetService.addAsset(asset);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(new ApiResponse<>("Asset added successfully!", assetId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetDef>> getAssetById(@RequestParam String id) {
        AssetDef asset = assetService.getAssetById(id);
        return ResponseEntity.ok(new ApiResponse<>("Asset retrieved successfully", asset));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<AssetDef>>> listAllAssets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AssetDef> assets = assetService.listAllAssets(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Asset list retrieved successfully", assets));
    }

    @GetMapping("/components")
    public ResponseEntity<ApiResponse<AssetDef>> getComponentsByAssetId(@RequestParam("id") String assetId) {
        AssetDef components = assetService.getComponentsByAssetId(assetId);
        return ResponseEntity.ok(new ApiResponse<>("Components retrieved successfully", components));
    }
}

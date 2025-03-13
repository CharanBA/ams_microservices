package com.optimas.relationshipservice.controller;

import com.optimas.relationshipservice.dto.ApiResponse;
import com.optimas.relationshipservice.service.RelationshipService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationshipController {

    private final RelationshipService relationshipService;

    public RelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @PostMapping("/link")
    public ResponseEntity<ApiResponse<String>> linkComponentToAsset(
            @RequestParam String assetId, @RequestParam String componentId) {
        String message = relationshipService.linkComponentToAsset(assetId, componentId);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(new ApiResponse<>("Component linked successfully", message));
    }

    @GetMapping("/components")
    public ResponseEntity<ApiResponse<List<Object>>> getComponentsByAssetId(@RequestParam String assetId) {
        List<Object> components = relationshipService.getComponentsByAssetId(assetId);
        return ResponseEntity.ok(new ApiResponse<>("Components retrieved successfully", components));
    }
}

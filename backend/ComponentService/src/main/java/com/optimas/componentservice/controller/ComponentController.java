package com.optimas.componentservice.controller;

import com.optimas.componentservice.dto.ApiResponse;
import com.optimas.componentservice.model.ComponentDef;
import com.optimas.componentservice.service.ComponentService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addComponent(@RequestBody ComponentDef component) {
        String componentId = componentService.addComponent(component);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(new ApiResponse<>("Component added successfully!", componentId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ComponentDef>> getComponentById(@RequestParam String id) {
        ComponentDef component = componentService.getComponentById(id);
        return ResponseEntity.ok(new ApiResponse<>("Component retrieved successfully", component));
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ComponentDef>>> listAllComponents() {
        List<ComponentDef> components = componentService.listAllComponents(); // Remove pagination
        return ResponseEntity.ok(new ApiResponse<>("Component list retrieved successfully", components));
    }
    
    
    
    
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ComponentDef>>> listAllComponents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ComponentDef> components = componentService.listAllComponents(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Component list retrieved successfully", components));
    }

    @GetMapping("/by-asset")
    public ResponseEntity<ApiResponse<List<ComponentDef>>> getComponentsByAssetId(@RequestParam("id") String assetId) {
    	
        List<ComponentDef> components = componentService.getComponentsByAssetId(assetId);
        System.out.println("heloo");
        return ResponseEntity.ok(new ApiResponse<>("Components retrieved successfully", components));
    }
}

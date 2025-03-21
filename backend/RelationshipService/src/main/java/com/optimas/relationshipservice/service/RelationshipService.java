package com.optimas.relationshipservice.service;

import com.optimas.relationshipservice.client.AssetServiceClient;

import java.util.Collections;
import java.util.List;

import com.optimas.relationshipservice.client.ComponentServiceClient;
import com.optimas.relationshipservice.dto.ApiResponse;
import com.optimas.relationshipservice.dto.ComponentDTO;
import com.optimas.relationshipservice.repository.RelationshipRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {

	private RelationshipRepository relationshipRepository;

	private AssetServiceClient assetServiceClient;

	private ComponentServiceClient componentServiceClient;

	public RelationshipService(RelationshipRepository relationshipRepository, AssetServiceClient assetServiceClient,
			ComponentServiceClient componentServiceClient) {
		super();
		this.relationshipRepository = relationshipRepository;
		this.assetServiceClient = assetServiceClient;
		this.componentServiceClient = componentServiceClient;
	}

	public String linkComponentToAsset(String assetId, String componentId) {
		ResponseEntity<String> assetResponse = assetServiceClient.getAssetById(assetId);
		if (!assetResponse.getStatusCode().is2xxSuccessful()) {
			return "Asset not found!";
		}

		ResponseEntity<String> componentResponse = componentServiceClient.getComponentById(componentId);
		if (!componentResponse.getStatusCode().is2xxSuccessful()) {
			return "Component not found!";
		}

		return relationshipRepository.createAssetComponentLink(assetId, componentId);
	}
	

	
	@CircuitBreaker(name = "getComponentsByAssetId", fallbackMethod = "getDefaultComponents")
    @Retry(name = "getComponentsByAssetId", fallbackMethod = "getDefaultComponents")
	public ApiResponse<List<ComponentDTO>> getComponentsByAssetId(String assetId) {
	    return componentServiceClient.getComponentsByAssetId(assetId).getBody();
	}
	
	 // Fallback method when ComponentService fails
    public ApiResponse<List<ComponentDTO>> getDefaultComponents(String assetId, Throwable throwable) {
        return new ApiResponse<>("Fallback: ComponentService is unavailable", Collections.emptyList());
    }

}

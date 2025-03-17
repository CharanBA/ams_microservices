package com.optimas.assetservice.service;

import com.optimas.assetservice.model.AssetDef;
import com.optimas.assetservice.repository.AssetRepository;
import com.optimas.assetservice.dto.ComponentDTO;
import com.optimas.assetservice.exception.AssetNotFoundException;
import com.optimas.assetservice.exception.DuplicateAssetException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

	private final AssetRepository assetRepository;

	public AssetService(AssetRepository assetRepository) {
		this.assetRepository = assetRepository;
	}

	public String addAsset(AssetDef asset) {
		if (assetRepository.getAssetByName(asset.getName()) != null) {
			throw new DuplicateAssetException("Asset with name '" + asset.getName() + "' already exists.");
		}
		return assetRepository.addAsset(asset);
	}

	public AssetDef getAssetById(String assetId) {
		AssetDef asset = assetRepository.getAssetById(assetId);
		if (asset == null) {
			throw new AssetNotFoundException("Asset with ID '" + assetId + "' not found.");
		}
		return asset;
	}

	public List<AssetDef> listAllAssets() {
		return assetRepository.listAllAssets();
	}

	public AssetDef getComponentsByAssetId(String assetId) {
		return assetRepository.getComponentsByAssetId(assetId);

	}

	public List<AssetDef> listAllAssets(int page, int size) {
		int offset = page * size;
		return assetRepository.listAllAssets(offset, size);
	}

	public long getTotalAssetCount() {
		return assetRepository.countAssets();
	}
}
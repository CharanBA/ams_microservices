package com.optimas.assetservice.repository;

import com.optimas.assetservice.client.ComponentServiceClient;
import com.optimas.assetservice.dto.ApiResponse;
import com.optimas.assetservice.dto.ComponentDTO;
import com.optimas.assetservice.exception.AssetNotFoundException;
import com.optimas.assetservice.model.AssetDef;
import com.optimas.assetservice.utility.OrientDBUtil;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class AssetRepository {
	@Autowired
	private ComponentServiceClient componentServiceClient;


	public String addAsset(AssetDef asset) {
		System.out.println("Saving asset to OrientDB...");
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			if (db.getClass("AssetDef") == null) {
				db.createVertexClass("AssetDef");
			}
			String assetId = UUID.randomUUID().toString();
			asset.setId(assetId);
			OVertex assetVertex = db.newVertex("AssetDef");
			assetVertex.setProperty("id", assetId);
			assetVertex.setProperty("name", asset.getName());
			assetVertex.setProperty("category", asset.getCategory());
			assetVertex.setProperty("description", asset.getDescription());
			assetVertex.setProperty("purchaseDate", asset.getPurchaseDate());
			assetVertex.setProperty("status", asset.getStatus());
			assetVertex.setProperty("assignedTo", asset.getAssignedTo());
			assetVertex.save();
			System.out.println("Asset saved successfully with ID: " + assetId);
			return assetId;

		} catch (Exception e) {
			System.err.println("Error saving asset: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public AssetDef getAssetById(String assetId) {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM AssetDef WHERE id = ?";
			OResultSet resultSet = db.query(query, assetId);
			if (resultSet.hasNext()) {
				OResult result = resultSet.next();
				AssetDef asset = new AssetDef();
				asset.setId(result.getProperty("id"));
				asset.setName(result.getProperty("name"));
				asset.setCategory(result.getProperty("category"));
				asset.setDescription(result.getProperty("description"));
				asset.setPurchaseDate(result.getProperty("purchaseDate"));
				asset.setStatus(result.getProperty("status"));
				asset.setAssignedTo(result.getProperty("assignedTo"));
				return asset;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AssetDef getAssetByName(String name) {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM AssetDef WHERE name = ?";
			OResultSet resultSet = db.query(query, name);

			if (resultSet.hasNext()) {
				OResult result = resultSet.next();
				AssetDef asset = new AssetDef();
				asset.setId(result.getProperty("id"));
				asset.setName(result.getProperty("name"));
				asset.setCategory(result.getProperty("category"));
				asset.setDescription(result.getProperty("description"));
				asset.setPurchaseDate(result.getProperty("purchaseDate"));
				asset.setStatus(result.getProperty("status"));
				asset.setAssignedTo(result.getProperty("assignedTo"));
				return asset;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public List<AssetDef> listAllAssets() {
//		List<AssetDef> assets = new ArrayList<>();
//		try (ODatabaseSession db = OrientDBUtil.getSession()) {
//			String query = "SELECT FROM AssetDef";
//			OResultSet resultSet = db.query(query);
//
//			while (resultSet.hasNext()) {
//				OResult result = resultSet.next();
//				AssetDef asset = new AssetDef();
//				asset.setId(result.getProperty("id"));
//				asset.setName(result.getProperty("name"));
//				asset.setCategory(result.getProperty("category"));
//				asset.setDescription(result.getProperty("description"));
//				asset.setPurchaseDate(result.getProperty("purchaseDate"));
//				asset.setStatus(result.getProperty("status"));
//				asset.setAssignedTo(result.getProperty("assignedTo"));
//				assets.add(asset);
//			}
//		} catch (Exception e) {
//			System.err.println("Error retrieving assets: " + e.getMessage());
//		}
//		return assets;
//	}

	public AssetDef getComponentsByAssetId(String assetId) {
		AssetDef asset = getAssetById(assetId);
		if (asset == null) {
			throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
		}

		ResponseEntity<ApiResponse<List<ComponentDTO>>> responseEntity = componentServiceClient
				.getComponentsByAssetId(assetId);

		System.out.println("hello");

//		if (responseEntity.getBody() != null && responseEntity.getBody().getData() != null) {
//			asset.setComponents(responseEntity.getBody().getData());
//		} else {
//			asset.setComponents(Collections.emptyList()); // Set empty list if no data
//		}

		return asset;
	}

	public List<AssetDef> listAllAssets(int offset, int limit) {
		List<AssetDef> assets = new ArrayList<>();

		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM AssetDef SKIP ? LIMIT ?";
			OResultSet resultSet = db.query(query, offset, limit);

			while (resultSet.hasNext()) {
				OResult result = resultSet.next();
				AssetDef asset = new AssetDef();
				asset.setId(result.getProperty("id"));
				asset.setName(result.getProperty("name"));
				asset.setCategory(result.getProperty("category"));
				asset.setDescription(result.getProperty("description"));
				asset.setPurchaseDate(result.getProperty("purchaseDate"));
				asset.setStatus(result.getProperty("status"));
				asset.setAssignedTo(result.getProperty("assignedTo"));
				assets.add(asset);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assets;
	}

	public long countAssets() {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT COUNT(*) as total FROM AssetDef";
			try (OResultSet resultSet = db.query(query)) {
				if (resultSet.hasNext()) {
					return resultSet.next().getProperty("total");
				}
			}
			return 0;
		} catch (Exception e) {
			System.err.println("Error counting assets: " + e.getMessage());
			return 0;
		}
	}

}

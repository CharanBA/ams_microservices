package com.optimas.componentservice.repository;

import com.optimas.componentservice.exception.ComponentNotFoundException;
import com.optimas.componentservice.model.ComponentDef;
import com.optimas.componentservice.utility.OrientDBUtil;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ComponentRepository {



	public String addComponent(ComponentDef component) {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			if (db.getClass("ComponentDef") == null) {
				db.createVertexClass("ComponentDef");
			}

			// Generate UUID for component
			String componentId = UUID.randomUUID().toString();
			component.setId(componentId);

			OVertex componentVertex = db.newVertex("ComponentDef");
			componentVertex.setProperty("id", component.getId());
			componentVertex.setProperty("name", component.getName());
			componentVertex.setProperty("category", component.getCategory());
			componentVertex.setProperty("manufacturer", component.getManufacturer());
			componentVertex.setProperty("serialNumber", component.getSerialNumber());
			componentVertex.setProperty("warrantyEnd", component.getWarrantyEnd());
			componentVertex.save();

			System.out.println("Component added successfully: " + componentId);
			return componentId;
		} catch (Exception e) {
			System.err.println("Error saving component: " + e.getMessage());
			return "Failed to add component.";
		}
	}

	public ComponentDef getComponentByName(String name) {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM ComponentDef WHERE name = ?";
			OResultSet resultSet = db.query(query, name);

			if (resultSet.hasNext()) {
				OResult result = resultSet.next();
				ComponentDef component = new ComponentDef();
				component.setId(result.getProperty("id"));
				component.setName(result.getProperty("name"));
				component.setCategory(result.getProperty("category"));
				component.setManufacturer(result.getProperty("manufacturer"));
				component.setSerialNumber(result.getProperty("serialNumber"));
				component.setWarrantyEnd(result.getProperty("warrantyEnd"));
				return component;
			}
		} catch (Exception e) {
			System.err.println("Error retrieving component by name: " + e.getMessage());
		}
		return null;
	}

	public Optional<ComponentDef> findById(String componentId) {
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM ComponentDef WHERE id = ?";
			OResultSet resultSet = db.query(query, componentId);

			if (resultSet.hasNext()) {
				OResult result = resultSet.next();
				ComponentDef component = new ComponentDef();
				component.setId(result.getProperty("id"));
				component.setName(result.getProperty("name"));
				component.setCategory(result.getProperty("category"));
				component.setManufacturer(result.getProperty("manufacturer"));
				component.setSerialNumber(result.getProperty("serialNumber"));
				component.setWarrantyEnd(result.getProperty("warrantyEnd"));
				return Optional.of(component);
			}
		} catch (Exception e) {
			System.err.println("Error retrieving component: " + e.getMessage());
		}
		return Optional.empty();
	}

	public List<ComponentDef> listAllComponents() {
		List<ComponentDef> components = new ArrayList<>();
		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM ComponentDef";
			OResultSet resultSet = db.query(query);

			while (resultSet.hasNext()) {
				OResult result = resultSet.next();
				ComponentDef component = new ComponentDef();
				component.setId(result.getProperty("id"));
				component.setName(result.getProperty("name"));
				component.setCategory(result.getProperty("category"));
				component.setManufacturer(result.getProperty("manufacturer"));
				component.setSerialNumber(result.getProperty("serialNumber"));
				component.setWarrantyEnd(result.getProperty("warrantyEnd"));
				components.add(component);
			}
		} catch (Exception e) {
			System.err.println("Error retrieving components: " + e.getMessage());
		}
		return components;
	}

	public List<ComponentDef> listAllComponents(int offset, int limit) {
		List<ComponentDef> components = new ArrayList<>();

		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			String query = "SELECT FROM ComponentDef SKIP ? LIMIT ?";
			OResultSet resultSet = db.query(query, offset, limit);

			while (resultSet.hasNext()) {
				OResult result = resultSet.next();
				ComponentDef component = new ComponentDef();
				component.setId(result.getProperty("id"));
				component.setName(result.getProperty("name"));
				component.setCategory(result.getProperty("category"));
				component.setManufacturer(result.getProperty("manufacturer"));
				component.setSerialNumber(result.getProperty("serialNumber"));
				component.setWarrantyEnd(result.getProperty("warrantyEnd"));
				components.add(component);
			}
			resultSet.close();
		} catch (Exception e) {
			System.err.println("Error retrieving components: " + e.getMessage());
		}
		return components;
	}

	public List<ComponentDef> getComponentsByAssetId(String assetId) {
		List<ComponentDef> components = new ArrayList<>();
		String query = "SELECT expand(out('HAS_COMPONENT')) FROM AssetDef WHERE id = ?";
		try (ODatabaseSession db = OrientDBUtil.getSession(); var resultSet = db.query(query, assetId)) {
			while (resultSet.hasNext()) {
				var element = resultSet.next().getElement().orElse(null);
				if (element instanceof ODocument componentDoc) {
					ComponentDef component = new ComponentDef();
					component.setId(componentDoc.getProperty("id"));
					component.setName(componentDoc.getProperty("name"));
					component.setCategory(componentDoc.getProperty("category"));
					component.setManufacturer(componentDoc.getProperty("manufacturer"));
					component.setSerialNumber(componentDoc.getProperty("serialNumber"));
					component.setWarrantyEnd(componentDoc.getProperty("warrantyEnd"));

					components.add(component);
				}
			}
		} catch (Exception e) {
			throw new ComponentNotFoundException(
					"Error fetching components for asset ID " + assetId + ": " + e.getMessage());
		}

		return components;
	}

}

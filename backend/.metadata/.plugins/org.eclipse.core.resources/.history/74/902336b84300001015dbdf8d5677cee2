package com.optimas.componentservice.service;

import com.optimas.componentservice.exception.ComponentAlreadyExistsException;
import com.optimas.componentservice.exception.ComponentNotFoundException;
import com.optimas.componentservice.exception.AssetNotFoundException;
import com.optimas.componentservice.model.ComponentDef;
import com.optimas.componentservice.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class ComponentService {
    
    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public String addComponent(ComponentDef component) {
        // Check if component already exists by name
        if (componentRepository.getComponentByName(component.getName()) != null) {
            throw new ComponentAlreadyExistsException("Component with name '" + component.getName() + "' already exists.");
        }

        // Call repository to add the component
        return componentRepository.addComponent(component);
    }


    public ComponentDef getComponentById(String componentId) {
        return componentRepository.findById(componentId)
                .orElseThrow(() -> new ComponentNotFoundException("Component with ID " + componentId + " not found."));
    }

//    public List<ComponentDef> listAllComponents() {
//        return componentRepository.listAllComponents();
//    }
    
    public List<ComponentDef> listAllComponents(int page, int size) {
        int offset = page * size;
        return componentRepository.listAllComponents(offset, size);
    }

    public String getServiceStatus() {
        return "Component Service is working!";
    }
    
   
    public List<ComponentDef> getComponentsByAssetId(String assetId) {
        List<ComponentDef> components = componentRepository.getComponentsByAssetId(assetId);
        if (components.isEmpty()) {
            throw new AssetNotFoundException("No components found for asset ID: " + assetId);
        }
        return components;
    }
    
   
}

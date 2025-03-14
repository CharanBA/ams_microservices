package com.optimas.assetservice.model;

import com.optimas.assetservice.dto.ComponentDTO;
import java.util.Date;
import java.util.List;


public class AssetDef {
    private String id;  
    private String name;
    private String category;
    private String description;
    private Date purchaseDate;
    private String status;
    private String assignedTo;
//    private List<ComponentDTO> components; 

    public AssetDef() {}

    public AssetDef(String id, String name, String category, String description, Date purchaseDate, String status, String assignedTo, List<ComponentDTO> components) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.assignedTo = assignedTo;
//        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

//    public List<ComponentDTO> getComponents() {
//        return components;
//    }
//
//    public void setComponents(List<ComponentDTO> components2) {
//        this.components = components2;
//    }

    @Override
    public String toString() {
        return "AssetDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", status='" + status + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
//                ", components=" + components +
                '}';
    }
}

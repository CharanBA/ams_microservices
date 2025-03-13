package com.optimas.assetservice.dto;

import java.util.List;

public class PaginatedResponse<T> extends ApiResponse<List<T>> {
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public PaginatedResponse(String message, List<T> data, long totalItems, int totalPages, int currentPage, int pageSize) {
        super(message, data);
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }
}

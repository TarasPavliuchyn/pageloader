package com.pageloader.service;

public interface PageLoaderService {
    void loadPages(Integer pageNumber);

    int countLoadedPages();
}

package com.pageloader.service.impl;

import com.pageloader.service.ConsoleService;
import com.pageloader.service.PageLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Autowired
    private PageLoaderService pageLoaderService;

    @Override
    @PostConstruct
    public void readConsole() {
        new Thread(() -> {
            System.out.println("Input number of pages: ");
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    Integer threadCount = Math.abs(sc.nextInt());
                    pageLoaderService.loadPages(threadCount);
                } else {
                    System.out.println("Only integers allowed");
                    sc.next();
                }
            }
        }).start();
    }

    @Override
    public void writeToConsole(String page) {
        int pageNumbers = pageLoaderService.countLoadedPages();
        System.out.printf("Loaded pages: %d\n%s\n", pageNumbers, page);
    }
}
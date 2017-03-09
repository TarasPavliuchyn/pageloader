package com.pageloader.task;

import com.pageloader.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Slf4j
public class PageLoadTask implements Runnable {

    private Element link;
    private ConsoleService consoleService;

    public PageLoadTask(Element link, ConsoleService consoleService) {
        this.link = link;
        this.consoleService = consoleService;
    }

    @Override
    public void run() {
        String href = link.attr("href");
        try {
            Document page = Jsoup.connect(href).get();
            consoleService.writeToConsole(page.html());
            System.out.println(Thread.currentThread().getName());
            if (log.isInfoEnabled()) {
                log.info("\nPage {} was successfully loaded by {}", href, Thread.currentThread().getName());
            }
        } catch (IOException e) {
            log.error("Page not found " + href, e);
        }
    }

}
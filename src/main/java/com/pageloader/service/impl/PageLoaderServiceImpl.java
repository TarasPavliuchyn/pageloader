package com.pageloader.service.impl;

import com.pageloader.factory.Attribute;
import com.pageloader.factory.PageContextFactory;
import com.pageloader.service.ConsoleService;
import com.pageloader.service.PageLoaderService;
import com.pageloader.task.PageLoadTask;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class PageLoaderServiceImpl implements PageLoaderService {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    private PageContextFactory contentFactory;
    @Autowired
    private ConsoleService consoleService;

    @Override
    public void loadPages(Integer pageNumber) {
        if (log.isInfoEnabled()) {
            log.info("Try to load {} pages", pageNumber);
        }
        try {
            Elements elements = contentFactory.getContext(Attribute.HREF);
            elements.stream()
                    .limit(pageNumber)
                    .map(link -> new PageLoadTask(link, consoleService))
                    .forEach(executorService::execute);
        } catch (IOException e) {
            log.error("Root page request was failed", e);
        }
    }

    @Override
    public int countLoadedPages() {
        return atomicInteger.incrementAndGet();
    }

}

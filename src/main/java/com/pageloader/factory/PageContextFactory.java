
package com.pageloader.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PageContextFactory {

    @Value("${resource.uri}")
    private String resourceUri;

    public Elements getContext(Attribute attribute) throws IOException {
        Document doc = Jsoup.connect(resourceUri).get();
        return doc.select(attribute.getAttr());
    }

}
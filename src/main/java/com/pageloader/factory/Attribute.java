package com.pageloader.factory;

public enum Attribute {
    HREF("a[href]");

    Attribute(String attr) {
        this.attr = attr;
    }

    private String attr;

    public String getAttr() {
        return attr;
    }
}
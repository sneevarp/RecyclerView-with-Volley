package com.example.android.recyclerview;

public class list {
    String headertext;
    String subtext;
    String url;

    public list(String headertext, String subtext,String url) {
        this.headertext = headertext;
        this.subtext = subtext;
        this.url=url;
    }

    public String getHeadertext() {
        return headertext;
    }

    public String getUrl() {
        return url;
    }

    public String getSubtext() {
        return subtext;
    }
}

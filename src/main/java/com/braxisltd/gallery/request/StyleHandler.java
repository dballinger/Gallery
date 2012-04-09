package com.braxisltd.gallery.request;

public class StyleHandler extends ClassPathResourceHandler {

    private static final String ROOT = "styles/";

    @Override
    protected String root() {
        return ROOT;
    }
}

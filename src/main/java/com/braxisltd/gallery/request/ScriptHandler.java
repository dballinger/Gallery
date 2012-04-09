package com.braxisltd.gallery.request;

public class ScriptHandler extends ClassPathResourceHandler {

    private static final String ROOT = "scripts/";

    @Override
    protected String root() {
        return ROOT;
    }
}

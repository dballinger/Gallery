package com.braxisltd.gallery.Domain;

import com.braxisltd.gallery.application.ApplicationConfig;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Heading {
    private static final String HEADING_FILE = "heading.txt";
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private String heading;
    private ApplicationConfig config;

    public Heading(ApplicationConfig config) {

        this.config = config;
    }

    public Heading load() throws IOException {
        File headingFile = new File(config.getDirectoryRoot(), HEADING_FILE);
        heading = Files.toString(headingFile, DEFAULT_CHARSET);
        return this;
    }

    public String getHeading() {
        if (heading == null) {
            throw new IllegalStateException("Load must first be called.");
        }
        return heading;
    }
}

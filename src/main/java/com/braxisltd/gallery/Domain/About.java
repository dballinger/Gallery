package com.braxisltd.gallery.Domain;

import com.braxisltd.gallery.application.ApplicationConfig;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class About {

    private static final String ABOUT_FILE = "about.txt";
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private ApplicationConfig config;
    private List<String> paragraphs;

    public About(ApplicationConfig config) {
        this.config = config;
    }

    public List<String> getParagraphs() {
        if (paragraphs == null) {
            throw new IllegalStateException("Must first call load().");
        }
        return paragraphs;
    }

    public About load() throws IOException {
        paragraphs = Files.readLines(new File(config.getDirectoryRoot(), ABOUT_FILE), DEFAULT_CHARSET);
        return this;
    }
}

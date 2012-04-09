package com.braxisltd.gallery.acceptance;

import com.braxisltd.gallery.application.ApplicationConfig;

public class TestConfig implements ApplicationConfig {

    public String getScheme() {
        return "http";
    }

    public String getHost() {
        return "localhost";
    }

    public int getPort() {
        return 8080;
    }

    public String getDirectoryRoot() {
        String classFileName = String.format("%s.class", getClass().getSimpleName());
        String pathToHere = getClass().getResource(classFileName).toExternalForm();
        return pathToHere.replace("file:/", "").replace(classFileName, "testimages");
    }
}

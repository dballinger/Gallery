package com.braxisltd.gallery.application;

public interface ApplicationConfig {
    String getScheme();
    String getHost();
    int getPort();
    String getDirectoryRoot();
}

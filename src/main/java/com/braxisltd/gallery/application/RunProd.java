package com.braxisltd.gallery.application;

import java.io.IOException;

public class RunProd {

    public static void main(String[] args) {
        try {
            new GalleryApplication(ConfigProvider.provide(ApplicationConfig.class)).start();
        } catch (IOException e) {
            e.printStackTrace();  //TODO: Auto-generated
        }
    }
}

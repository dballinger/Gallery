package com.braxisltd.gallery.application;

import java.io.IOException;

public class RunDev {

    public static void main(String[] args) {
        try {
            new GalleryApplication(
                    new ApplicationConfig() {
                        @Override
                        public String getScheme() {
                            return "http";
                        }

                        @Override
                        public String getHost() {
                            return "localhost";
                        }

                        @Override
                        public int getPort() {
                            return 80;
                        }

                        @Override
                        public String getDirectoryRoot() {
                            return "C:\\Users\\Darren\\IdeaProjects\\Gallery\\src\\test\\resources\\com\\braxisltd\\gallery\\acceptance\\testimages";
                        }
                    }
            ).start();
        } catch (IOException e) {
            e.printStackTrace();  //TODO: Auto-generated
        }
    }
}

package com.braxisltd.gallery.request.stuff;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Utils {
    public static FileFilter directoryFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
    }

    public static FilenameFilter imageFilter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.endsWith(".txt");
            }
        };
    }
}

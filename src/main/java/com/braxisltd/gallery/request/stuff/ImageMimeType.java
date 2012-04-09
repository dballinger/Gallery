package com.braxisltd.gallery.request.stuff;

import java.io.File;

import static java.util.regex.Pattern.quote;

public enum ImageMimeType {
    JPEG,
    PNG,
    GIF,
    UNKNOWN {
        @Override
        public String contentType() {
            return "image";
        }
    };

    public static ImageMimeType fromFile(File file) {
        String[] parts = file.getName().split(quote("."));
        String extension = parts[parts.length - 1];
        for (ImageMimeType type : values()) {
            if (type.name().toLowerCase().equals(extension)) {
                return type;
            }
        }
        if (extension.equals("jpg")) {
            return JPEG;
        }
        return UNKNOWN;
    }
    
    public String contentType() {
        return "image/" + name().toLowerCase();
    }
}

package com.braxisltd.gallery.Domain;

import static com.braxisltd.gallery.request.ImageHandler.IMAGE_ROOT;
import static java.util.regex.Matcher.quoteReplacement;
import static java.util.regex.Pattern.quote;

public class Image {
    
    private final String name;
    private final String directory;

    public Image(String name, Category category) {
        this.name = name;
        directory = category.getKey();
    }
    
    public String getId() {
        return name.replaceAll(quote("."), quoteReplacement("_"));
    }

    public String getName() {
        return name;
    }
    
    public String getMainUrl() {
        return String.format("%s%s/%s", IMAGE_ROOT, directory, name);
    }
}

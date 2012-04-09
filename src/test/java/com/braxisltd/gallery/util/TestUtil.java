package com.braxisltd.gallery.util;

import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.application.ApplicationConfig;
import com.google.common.base.Splitter;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.google.common.base.CharMatcher.WHITESPACE;
import static com.google.common.collect.Iterables.contains;

public class TestUtil {
    public static boolean elementHasClass(WebElement element, String cssClass) {
        String classes = element.getAttribute("class");
        if (classes == null) {
            return false;
        }
        return contains(Splitter.on(WHITESPACE).split(classes), cssClass);
    }

    public static String[] imagesFor(Category category, ApplicationConfig config) {
        File root = new File(config.getDirectoryRoot(), category.getKey());
        return root.list();
    }
}

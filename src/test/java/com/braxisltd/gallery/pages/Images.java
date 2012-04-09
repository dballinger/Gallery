package com.braxisltd.gallery.pages;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.regex.Pattern.quote;

public class Images extends PageTestContext {

    @FindBy(css = ".galleria-thumbnails .galleria-image img")
    private List<WebElement> imagesElements;

    public Images(WebDriver driver) {
        super(driver);
    }

    public static Matcher<Images> areShowing(final String[] imageNames) {
        return new TypeSafeMatcher<Images>() {
            @Override
            protected boolean matchesSafely(Images images) {
                HashSet<String> names = newHashSet(imageNames);
                for (WebElement image : images.imagesElements) {
                    String[] parts = image.getAttribute("src").split(quote("/"));
                    if (!names.remove(parts[parts.length - 1])) {
                        return false;
                    }
                }
                return names.isEmpty();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Images ").appendValue(imageNames);
            }
        };
    }

    @Override
    public String toString() {
        String images = Joiner.on(" | ").join(Lists.transform(imagesElements, new Function<WebElement, String>() {
            @Override
            public String apply(@Nullable WebElement element) {
                return element.getAttribute("src");
            }
        }));
        return "Images:[" +
                images +
                ']';
    }
}

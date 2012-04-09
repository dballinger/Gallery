package com.braxisltd.gallery.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Matchers {
    
    public static TypeSafeMatcher<List<WebElement>> listItemContainsText(final String text) {
        return new TypeSafeMatcher<List<WebElement>>() {
            @Override
            protected boolean matchesSafely(List<WebElement> webElements) {
                for (WebElement webElement : webElements) {
                    if (webElement.getText().contains(text)) {
                        return true;
                    }
                }
                return false;
            }
            public void describeTo(Description description) {
                description.appendText("List item containing text ").appendValue(text);
            }
        };
    }
}

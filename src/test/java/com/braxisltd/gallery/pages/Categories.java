package com.braxisltd.gallery.pages;

import com.braxisltd.gallery.Domain.Category;
import com.google.common.base.Predicate;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nullable;
import java.util.List;

import static com.braxisltd.gallery.util.TestUtil.elementHasClass;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;

public class Categories extends PageTestContext {

    @FindBy(css = "#categories li")
    private List<WebElement> categoryItems;

    public Categories(WebDriver driver) {
        super(driver);
    }

    public static Matcher<Categories> containsHiddenCategory(final Category category) {
        return new TypeSafeMatcher<Categories>() {
            @Override
            protected boolean matchesSafely(Categories categories) {
                for (WebElement candidate : categories.categoryItems) {
                    if (categoryLinkContains(candidate, category)) {
                        return !candidate.isDisplayed();
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("To find hidden category ").appendValue(category);
            }
        };
    }

    private static boolean categoryLinkContains(WebElement candidate, Category category) {
        return candidate.findElement(tagName("a")).getAttribute("href").contains(category.getUrl());
    }

    public static TypeSafeMatcher<Categories> hasSelectedCategory(final Category category) {
        return new TypeSafeMatcher<Categories>() {
            @Override
            protected boolean matchesSafely(Categories categories) {
                for (WebElement candidate : categories.categoryItems) {
                    if (categoryLinkContains(candidate, category) && !elementHasClass(candidate, "selected")
                            || !categoryLinkContains(candidate, category) && elementHasClass(candidate, "selected")) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Selected category ").appendValue(category);
            }
        };
    }

    public void select(final Category category) {
        for (WebElement categoryItem : categoryItems) {
            if (categoryItem.getText().equals(category.getName())) {
                categoryItem.findElement(cssSelector("a")).click();
                break;
            }
        }
        waitFor(new Predicate<WebDriver>() {
            @Override
            public boolean apply(@Nullable WebDriver input) {
                return hasSelectedCategory(category).matches(Categories.this);
            }
        });
    }
}

package com.braxisltd.gallery.pages;

import com.braxisltd.gallery.Domain.About;
import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.Domain.Heading;
import com.braxisltd.gallery.application.ApplicationConfig;
import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

import static com.braxisltd.gallery.pages.Categories.containsHiddenCategory;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;

public class GalleryPage extends PageTestContext {

    private final String pageUrl;
    private final Categories categories;
    private final Images images;
    @FindBy(id = "categories")
    private WebElement categoryContainer;
    @FindBy(css = "#navigation li.categories")
    private WebElement categoriesLabel;
    @FindBy(id = "about")
    private WebElement about;
    @FindBy(css = ".heading h1")
    private WebElement heading;

    public GalleryPage(WebDriver driver, ApplicationConfig config) {
        super(driver);
        categories = create(Categories.class);
        images = create(Images.class);
        pageUrl = String.format("%s://%s:%s", config.getScheme(), config.getHost(), config.getPort());
        PageFactory.initElements(driver, this);
    }

    public GalleryPage goTo() {
        super.goTo(pageUrl);
        return this;
    }

    public GalleryPage assertPageContainsInvisibleCategory(Category category) {
        assertThat(categories, containsHiddenCategory(category));
        return this;
    }

    public GalleryPage assertImagesShownAre(String... imageNames) {
        assertThat(images, Images.areShowing(imageNames));
        return this;
    }

    public GalleryPage selectCategory(Category category) {
        Actions actions = new Actions(driver());
        actions.moveToElement(categoriesLabel).perform();
        waitFor(new Predicate<WebDriver>() {
            @Override
            public boolean apply(@Nullable WebDriver input) {
                return categoryContainer.isDisplayed();
            }
        });
        categories.select(category);
        waitForGallery();
        return this;
    }

    private void waitForGallery() {
        waitFor(new Predicate<WebDriver>() {
            @Override
            public boolean apply(@Nullable WebDriver driver) {
                return driver.findElement(cssSelector(".galleria-container")).isDisplayed();
            }
        });
    }

    public void assertAboutTextPresent(ApplicationConfig config) throws IOException {
        List<String> expectedParagraphTexts = new About(config).load().getParagraphs();
        List<WebElement> paragraphs = about.findElements(tagName("p"));
        for (WebElement paragraph : paragraphs) {
            String text = paragraph.getText();
            assertThat(expectedParagraphTexts, hasItem(text));
            expectedParagraphTexts.remove(text);
        }
        assertThat(expectedParagraphTexts.size(), is(0));
    }

    public void assertHeadingPresent(ApplicationConfig config) throws IOException {
        String headingText = new Heading(config).load().getHeading();
        assertThat(heading.getText(), is(headingText));
    }
}

package com.braxisltd.gallery.acceptance;

import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.pages.GalleryPage;
import com.braxisltd.gallery.util.TestUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GalleryTest extends PageTest {

    private static final Category CATEGORY_ONE = Category.fromName("Category One");
    private static final Category CATEGORY_TWO = Category.fromName("Category Two");

    @BeforeClass
    public static void beforeClass() throws Exception {
        start(new TestConfig());
    }

    @Before
    public void before() throws Exception {
        galleryPage = new GalleryPage(driver, config);
        galleryPage.goTo();
    }

    @Test
    public void shouldLoadTheCategoriesAndShowImagesInCategories() throws Exception {
        galleryPage.assertHeadingPresent(config);
        galleryPage.assertAboutTextPresent(config);
        galleryPage.assertPageContainsInvisibleCategory(CATEGORY_ONE);
        galleryPage.assertPageContainsInvisibleCategory(CATEGORY_TWO);
        galleryPage.selectCategory(CATEGORY_ONE);
        galleryPage.assertHeadingPresent(config);
        galleryPage.assertImagesShownAre(TestUtil.imagesFor(CATEGORY_ONE, config));
        galleryPage.selectCategory(CATEGORY_TWO);
        galleryPage.assertHeadingPresent(config);
        galleryPage.assertImagesShownAre(TestUtil.imagesFor(CATEGORY_TWO, config));
    }
}

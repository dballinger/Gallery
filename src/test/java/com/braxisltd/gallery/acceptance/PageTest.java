package com.braxisltd.gallery.acceptance;

import com.braxisltd.gallery.application.ApplicationConfig;
import com.braxisltd.gallery.application.GalleryApplication;
import com.braxisltd.gallery.pages.GalleryPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageTest {
    protected static WebDriver driver;
    protected static GalleryApplication application;
    protected static ApplicationConfig config;
    protected GalleryPage galleryPage;

    public static void start(ApplicationConfig config) throws Exception {
        PageTest.config = config;
        driver = new ChromeDriver();
        application = new GalleryApplication(config).start();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        driver.close();
        application.stop();
    }

    @Before
    public void before() throws Exception {
        galleryPage = new GalleryPage(driver, config);
        galleryPage.goTo();
    }
}

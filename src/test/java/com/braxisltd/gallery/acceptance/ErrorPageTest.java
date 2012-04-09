package com.braxisltd.gallery.acceptance;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ErrorPageTest extends PageTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        start(new TestConfig() {
            @Override
            public String getDirectoryRoot() {
                throw new RuntimeException();
            }
        });
    }

    @Test
    public void shouldShowErrorPage() throws Exception {
        assertThat(driver.getPageSource().toLowerCase(), containsString("an error has occurred"));
    }

}

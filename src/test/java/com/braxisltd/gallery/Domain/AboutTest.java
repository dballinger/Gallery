package com.braxisltd.gallery.Domain;

import com.braxisltd.gallery.acceptance.TestConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class AboutTest {

    private About about;

    @Before
    public void before() throws Exception {
        about = new About(new TestConfig());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfNotLoaded() throws Exception {
        about.getParagraphs();
    }

    @Test
    public void shouldLoadParagraphs() throws Exception {
        about.load();
        assertThat(about.getParagraphs(), hasItem("This is some text about the site."));
        assertThat(about.getParagraphs(), hasItem("This is another paragraph."));
    }
}

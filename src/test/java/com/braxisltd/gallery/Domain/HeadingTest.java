package com.braxisltd.gallery.Domain;

import com.braxisltd.gallery.acceptance.TestConfig;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HeadingTest {

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenNotLoaded() throws Exception {
        new Heading(new TestConfig()).getHeading();
    }

    @Test
    public void shouldLoadHeading() throws Exception {
        String heading = new Heading(new TestConfig()).load().getHeading();
        assertThat(heading, is("A selection of images."));
    }
}

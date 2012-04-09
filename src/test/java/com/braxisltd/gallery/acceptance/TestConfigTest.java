package com.braxisltd.gallery.acceptance;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class TestConfigTest {

    @Test
    public void shouldFindImageRoot() throws Exception {
        String imageRoot = new TestConfig().getDirectoryRoot();
        System.out.println(imageRoot);
        List<String> list = newArrayList(new File(imageRoot).list());
        assertThat(list, hasItem("Category_One"));
        assertThat(list, hasItem("Category_Two"));
    }
}

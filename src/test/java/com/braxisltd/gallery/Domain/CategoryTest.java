package com.braxisltd.gallery.Domain;

import org.junit.Test;

import static com.braxisltd.gallery.Domain.Category.fromKey;
import static com.braxisltd.gallery.Domain.Category.fromName;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CategoryTest {

    private static final String KEY = "abc_def";
    private static final String NAME = "abc def";

    @Test
    public void shouldEqualWhenGeneratedFromDifferentMethods() throws Exception {
        assertThat(fromKey(KEY), is(fromName(NAME)));
    }

    @Test
    public void shouldGenerateName() throws Exception {
        assertThat(fromKey(KEY).getName(), is(NAME));
    }
    
    @Test
    public void shouldGetKey() throws Exception {
      assertThat(fromKey(KEY).getKey(), is(KEY));
    }
}

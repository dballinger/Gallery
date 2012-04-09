package com.braxisltd.gallery.application;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConfigProviderTest {

    @Test
    public void shouldLoadApplicationConfig() throws Exception {
        ApplicationConfig config = ConfigProvider.provide(ApplicationConfig.class);
        assertThat(config.getScheme(), is("http"));
        assertThat(config.getHost(), is("localhost"));
        assertThat(config.getPort(), is(80));
        assertThat(config.getDirectoryRoot(), is("root"));
    }

    @Test
    public void shouldThrowIfPropertyNotDefined() throws Exception {
        try {
            ConfigProvider.provide(BadConfig.class).getDoesNotExist();
        } catch (Exception e) {
            assertThat(e, is(ConfigurationException.class));
            return;
        }
        fail();
    }

    public interface BadConfig {
        String getDoesNotExist();
    }

}

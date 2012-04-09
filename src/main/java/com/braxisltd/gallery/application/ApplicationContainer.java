package com.braxisltd.gallery.application;

import com.braxisltd.gallery.request.*;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class ApplicationContainer implements Container {

    private final ApplicationConfig config;

    public ApplicationContainer(ApplicationConfig config) {
        this.config = config;
    }

    public void handle(Request request, Response response) {
        new RequestController(
                new AboutHandler(config),
                new GalleryHandler(config),
                new ImageHandler(config),
                new StyleHandler(),
                new ScriptHandler())
                .handleRequest(request, response);
    }
}

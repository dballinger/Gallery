package com.braxisltd.gallery.request;

import com.braxisltd.gallery.application.ApplicationConfig;
import com.braxisltd.gallery.request.stuff.ImageMimeType;
import com.google.common.io.Files;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.File;
import java.io.OutputStream;

import static java.util.regex.Pattern.quote;

public class ImageHandler implements RequestHandler {

    public static final String IMAGE_ROOT = "/images/";

    private final ApplicationConfig config;

    public ImageHandler(ApplicationConfig config) {
        this.config = config;
    }

    @Override
    public boolean canHandle(Request request) {
        return request.getTarget().startsWith(IMAGE_ROOT);
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        File image = new File(config.getDirectoryRoot(), request.getTarget().replaceFirst(quote(IMAGE_ROOT), ""));
        byte[] imageData = Files.toByteArray(image);
        long time = System.currentTimeMillis();
        response.set("Content-Type", ImageMimeType.fromFile(image).contentType());
        response.set("Server", "SimpleHelloWorld/1.0 (Simple 4.0)");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
        OutputStream body = response.getOutputStream();
        body.write(imageData);
        body.close();
    }

}

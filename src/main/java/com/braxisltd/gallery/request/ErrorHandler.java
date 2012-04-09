package com.braxisltd.gallery.request;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.OutputStreamWriter;

public class ErrorHandler implements RequestHandler {
    public ErrorHandler(Exception e) {
    }

    @Override
    public boolean canHandle(Request request) {
        return false;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        writer.append("Sorry, an error has occurred.");
        writer.flush();
        writer.close();
    }
}

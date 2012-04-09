package com.braxisltd.gallery.request;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public interface RequestHandler {
    boolean canHandle(Request request);
    void handle(Request request, Response response) throws Exception;
}

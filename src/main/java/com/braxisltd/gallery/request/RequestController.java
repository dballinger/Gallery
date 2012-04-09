package com.braxisltd.gallery.request;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public class RequestController {

    private RequestHandler[] handlers;

    public RequestController(RequestHandler... handlers) {
        this.handlers = handlers;
    }

    public void handleRequest(Request request, Response response) {
        try {
            for (RequestHandler handler : handlers) {
                if (handler.canHandle(request)) {
                    handler.handle(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            //TODO log this properly
            e.printStackTrace();
            try {
                new ErrorHandler(e).handle(request, response);
            } catch (Exception e1) {
                /*Oh dear!*/
                e1.printStackTrace();
            }
        }
    }
}

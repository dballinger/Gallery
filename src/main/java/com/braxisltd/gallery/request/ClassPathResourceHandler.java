package com.braxisltd.gallery.request;

import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.OutputStream;
import java.net.URL;

import static java.util.regex.Pattern.quote;

public abstract class ClassPathResourceHandler implements RequestHandler {
    @Override
    public void handle(Request request, Response response) throws Exception {
        URL resource = Resources.getResource(getClass(), String.format("%s%s", root(), file(request)));
        long time = System.currentTimeMillis();
        response.set("Server", "SimpleHelloWorld/1.0 (Simple 4.0)");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
        OutputStream out = response.getOutputStream();
        ByteStreams.copy(resource.openStream(), out);
        out.close();
    }

    @Override
    public boolean canHandle(Request request) {
        return request.getTarget().startsWith(String.format("/%s", root()));
    }

    private String file(Request request) {
        return request.getTarget().replaceFirst(quote("/"), "").replaceFirst(quote(root()), "");
    }

    protected abstract String root();
}

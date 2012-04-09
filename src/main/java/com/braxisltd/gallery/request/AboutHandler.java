package com.braxisltd.gallery.request;

import com.braxisltd.gallery.Domain.About;
import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.Domain.Heading;
import com.braxisltd.gallery.application.ApplicationConfig;
import com.braxisltd.gallery.request.models.AboutModel;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.List;

public class AboutHandler extends ViewRequestHandler {

    public AboutHandler(ApplicationConfig config) {
        super(config);
    }

    @Override
    public boolean canHandle(Request request) {
        return request.getTarget().equals("/");
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        List<Category> categories = loadCategories();
        String heading = new Heading(config()).load().getHeading();
        view("about.ftl")
                .withModel("model", new AboutModel(new About(config()).load(), heading, categories))
                .render(response);
    }

}

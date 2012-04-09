package com.braxisltd.gallery.request;

import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.Domain.Heading;
import com.braxisltd.gallery.Domain.Image;
import com.braxisltd.gallery.application.ApplicationConfig;
import com.braxisltd.gallery.request.models.GalleryModel;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;

import static com.braxisltd.gallery.request.stuff.Utils.imageFilter;
import static com.google.common.collect.Lists.newArrayList;

public class GalleryHandler extends ViewRequestHandler {

    public GalleryHandler(ApplicationConfig config) {
        super(config);
    }

    @Override
    public boolean canHandle(Request request) {
        return request.getTarget().startsWith("/categories/");
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        File imageRoot = new File(config().getDirectoryRoot());
        List<Category> categories = loadCategories();
        String heading = new Heading(config()).load().getHeading();
        view("gallery.ftl")
                .withModel("model", new GalleryModel(heading, categories, images(imageRoot, parseCategory(request, categories))))
                .render(response);
    }

    private Category parseCategory(Request request, final List<Category> categories) {
        final List<String> segments = newArrayList(Splitter.on("/").omitEmptyStrings().split(request.getTarget()));
        if (segments.size() > 1 && segments.get(0).equals("categories")) {
            Optional<Category> categoryOptional = Iterables.tryFind(categories, new Predicate<Category>() {
                @Override
                public boolean apply(@Nullable Category category) {
                    return category.getKey().equals(segments.get(1));
                }
            });
            return categoryOptional.isPresent() ? categoryOptional.get().makeSelected() : categories.get(0).makeSelected();
        }
        return categories.get(0).makeSelected();
    }

    private List<Image> images(File imageRoot, final Category category) {
        List<String> fileNames = newArrayList(
                new File(imageRoot, category.getKey()).list(imageFilter())
        );
        return Lists.transform(fileNames, new Function<String, Image>() {
            @Override
            public Image apply(@Nullable String input) {
                return new Image(input, category);
            }
        });
    }
}

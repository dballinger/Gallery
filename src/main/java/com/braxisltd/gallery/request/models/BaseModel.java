package com.braxisltd.gallery.request.models;

import com.braxisltd.gallery.Domain.Category;

import java.util.List;

public class BaseModel {

    private final String heading;
    protected final List<Category> categories;

    public BaseModel(String heading, List<Category> categories) {
        this.heading = heading;
        this.categories = categories;
    }

    public String getHeading() {
        return heading;
    }

    public List<Category> getCategories() {
        return categories;
    }
}

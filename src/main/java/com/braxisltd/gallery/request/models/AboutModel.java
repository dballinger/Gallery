package com.braxisltd.gallery.request.models;

import com.braxisltd.gallery.Domain.About;
import com.braxisltd.gallery.Domain.Category;

import java.util.List;

public class AboutModel extends BaseModel {
    private About about;

    public AboutModel(About about, String heading, List<Category> categories) {
        super(heading, categories);
        this.about = about;
    }

    public About getAbout() {
        return about;
    }
}

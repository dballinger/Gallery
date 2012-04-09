package com.braxisltd.gallery.request.models;

import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.Domain.Image;

import java.util.List;

public class GalleryModel extends BaseModel {

    private List<Image> images;

    public GalleryModel(String heading, List<Category> categories, List<Image> images) {
        super(heading, categories);
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }
}

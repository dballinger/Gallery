package com.braxisltd.gallery.Domain;

import static java.util.regex.Matcher.quoteReplacement;
import static java.util.regex.Pattern.quote;

public class Category {
    
    private final String key;
    private boolean selected;

    private Category(String key) {
        this.key = key;
    }

    public static Category fromKey(String key) {
        return new Category(key);
    }

    public static Category fromName(String name) {
        return new Category(name.replaceAll(quote(" "), quoteReplacement("_")));
    }

    public String getKey() {
        return key;
    }
    
    public String getName() {
        return key.replaceAll(quote("_"), quoteReplacement(" "));
    }
    
    public String getUrl() {
        return "/categories/" + key;
    }

    public boolean isSelected() {
        return selected;
    }

    public Category makeSelected() {
        this.selected = true;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return !(key != null ? !key.equals(category.key) : category.key != null);
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}

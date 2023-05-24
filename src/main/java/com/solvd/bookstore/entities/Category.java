package com.solvd.bookstore.entities;

public enum Category {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    public static Category getCategoryByName(String categoryName) {
        for (Category category : Category.values()) {
            if (category.getLabel().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category name: " + categoryName);
    }
}


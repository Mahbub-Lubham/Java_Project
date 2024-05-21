package com.cookbook;

public class NonVegRecipe extends Recipe {
    public NonVegRecipe(String title, String description) {
        super(title, description);
    }

    @Override
    public void displayRecipe() {
        System.out.println("Non-Vegetarian Recipe: " + getTitle());
        System.out.println("Description: " + getDescription());
        System.out.println("Ingredients: " + String.join(", ", getIngredients()));
        System.out.println("Average Rating: " + getAverageRating());
    }
}

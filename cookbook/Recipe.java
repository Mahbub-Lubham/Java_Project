package com.cookbook;
import java.util.ArrayList;
import java.util.List;

public abstract class Recipe {
    private String title;
    private String description;
    private List<String> ingredients;
    private List<Integer> ratings;

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    public abstract void displayRecipe();
}

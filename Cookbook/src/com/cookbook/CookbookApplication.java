package com.cookbook;
import java.util.Scanner;

public class CookbookApplication {
    private static UserManager userManager = new UserManager();
    private static RecipeManager recipeManager = new RecipeManager();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        register(scanner);
                        break;
                    case 2:
                        login(scanner);
                        break;
                    case 3:
                        System.exit(0);
                }
            } else {
                System.out.println("1. Create Recipe");
                System.out.println("2. View Recipes");
                System.out.println("3. Search Recipes by Title");
                System.out.println("4. Search Recipes by Ingredient");
                System.out.println("5. Add Ingredient to Recipe");
                System.out.println("6. Rate Recipe");
                System.out.println("7. Update Profile");
                System.out.println("8. Manage Favorites");
                System.out.println("9. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        createRecipe(scanner);
                        break;
                    case 2:
                        viewRecipes();
                        break;
                    case 3:
                        searchRecipesByTitle(scanner);
                        break;
                    case 4:
                        searchRecipesByIngredient(scanner);
                        break;
                    case 5:
                        addIngredientToRecipe(scanner);
                        break;
                    case 6:
                        rateRecipe(scanner);
                        break;
                    case 7:
                        updateProfile(scanner);
                        break;
                    case 8:
                        manageFavorites(scanner);
                        break;
                    case 9:
                        currentUser = null;
                        break;
                }
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        if (userManager.registerUser(username, password, email)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        currentUser = userManager.loginUser(username, password);
        if (currentUser != null) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void createRecipe(Scanner scanner) {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        System.out.println("Enter type (vegetarian/non-vegetarian):");
        String type = scanner.nextLine();
        Recipe recipe;
        if (type.equalsIgnoreCase("vegetarian")) {
            recipe = new VegRecipe(title, description);
        } else {
            recipe = new NonVegRecipe(title, description);
        }
        recipeManager.addRecipe(recipe);
        System.out.println("Recipe created successfully.");
    }

    private static void viewRecipes() {
        for (Recipe recipe : recipeManager.getRecipes()) {
            recipe.displayRecipe();
        }
    }

    private static void searchRecipesByTitle(Scanner scanner) {
        System.out.println("Enter title to search:");
        String title = scanner.nextLine();
        for (Recipe recipe : recipeManager.searchRecipesByTitle(title)) {
            recipe.displayRecipe();
        }
    }

    private static void searchRecipesByIngredient(Scanner scanner) {
        System.out.println("Enter ingredient to search:");
        String ingredient = scanner.nextLine();
        for (Recipe recipe : recipeManager.searchRecipesByIngredient(ingredient)) {
            recipe.displayRecipe();
        }
    }

    private static void addIngredientToRecipe(Scanner scanner) {
        System.out.println("Enter recipe title to add ingredient:");
        String title = scanner.nextLine();
        for (Recipe recipe : recipeManager.searchRecipesByTitle(title)) {
            System.out.println("Enter ingredient to add:");
            String ingredient = scanner.nextLine();
            recipe.addIngredient(ingredient);
            System.out.println("Ingredient added successfully.");
        }
    }

    private static void rateRecipe(Scanner scanner) {
        System.out.println("Enter recipe title to rate:");
        String title = scanner.nextLine();
        for (Recipe recipe : recipeManager.searchRecipesByTitle(title)) {
            System.out.println("Enter rating (1-5):");
            int rating = scanner.nextInt();
            scanner.nextLine();
            recipe.addRating(rating);
            System.out.println("Rating added successfully.");
        }
    }

    private static void updateProfile(Scanner scanner) {
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        if (userManager.updateUserEmail(currentUser.getUsername(), email)) {
            System.out.println("Email updated successfully.");
        } else {
            System.out.println("Failed to update email.");
        }
    }

    private static void manageFavorites(Scanner scanner) {
        System.out.println("1. Add Favorite Recipe");
        System.out.println("2. View Favorite Recipes");
        System.out.println("3. Remove Favorite Recipe");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter recipe title to add to favorites:");
                String addTitle = scanner.nextLine();
                for (Recipe recipe : recipeManager.searchRecipesByTitle(addTitle)) {
                    currentUser.addFavoriteRecipe(recipe);
                    System.out.println("Recipe added to favorites.");
                }
                break;
            case 2:
                for (Recipe recipe : currentUser.getFavoriteRecipes()) {
                    recipe.displayRecipe();
                }
                break;
            case 3:
                System.out.println("Enter recipe title to remove from favorites:");
                String removeTitle = scanner.nextLine();
                for (Recipe recipe : currentUser.getFavoriteRecipes()) {
                    if (recipe.getTitle().equalsIgnoreCase(removeTitle)) {
                        currentUser.removeFavoriteRecipe(recipe);
                        System.out.println("Recipe removed from favorites.");
                        break;
                    }
                }
                break;
        }
    }
}

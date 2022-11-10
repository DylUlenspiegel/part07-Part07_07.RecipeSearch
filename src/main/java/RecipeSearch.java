import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeSearch {
    private String nameOfFile;
    private String nameOfRecipe;
    private int cookTime;
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> parts = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();


    public void read() {
        System.out.println("File to read: ");
        Scanner name = new Scanner(System.in);
        nameOfFile = name.nextLine();
        try (Scanner scanner = new Scanner(Paths.get(nameOfFile))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                parts.add(row);
                if (row.isEmpty()) {
                    nameOfRecipe = parts.get(0);
                    cookTime = Integer.parseInt(parts.get(1));
                    for (int i = 2; i < parts.size(); i++) {
                        ingredients.add(parts.get(i));
                    }
                    Recipe recipe = new Recipe(nameOfRecipe, cookTime, ingredients);
                    recipes.add(recipe);
                    parts = new ArrayList<>();
                    ingredients = new ArrayList<>();
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void commands() {
        System.out.println("Commands: ");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("list")) {
                System.out.println("Recipes:");
                printRecipes();
            }
            if (command.equals("stop")) {
                break;
            }
            if (command.equals("find name")) {
                System.out.println("Searched word: ");
                String word = scanner.nextLine();
                for (Recipe recipe : recipes
                ) {
                    if (recipe.getName().matches(word)) {
                        System.out.println(recipe);
                    }
                }
            }
            if (command.equals("find cooking time")) {
                System.out.println("Max cooking time: ");
                cookTime = scanner.nextInt();
                for (Recipe recipe : recipes
                ) {
                    if (recipe.getCookTime() < cookTime) {
                        System.out.println(recipe);
                    }
                }
            }
            if (command.equals("find ingredient")) {
                System.out.println("Ingredient: ");
                String word = scanner.nextLine();
                for (Recipe recipe : recipes
                ) {
                    if (recipe.getIngredients().contains(word)) {
                        System.out.println(recipe);
                    }
                }
            }
        }
    }

    public void printRecipes() {
        for (Recipe recipe :
                recipes) {
            System.out.println(recipe);
        }

    }
}
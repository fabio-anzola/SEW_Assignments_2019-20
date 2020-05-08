package UE17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * The Cocktailbar class
 *
 * @author fabioanzola
 */
public class Cocktailbar {
    public static Set<String> getAvailableDrinks(String filename) throws IOException {
        List<String> readFile = Files.readAllLines(Paths.get(filename));
        Map<String, List<String>> recipes = new HashMap<>();
        Set<String> availableIngredients = new HashSet<>();
        Set<String> availableDrinks = new HashSet<>();
        Set<String> makeable = new HashSet<>();
        int endOfProducts = 0;

        for (int i = 0; i < readFile.size(); i++) {
            if (!readFile.get(i).contains("#")) {
                if (readFile.get(i).contains(":")) {
                    String key = readFile.get(i).split(":")[0].trim();
                    List<String> value = Arrays.asList(readFile.get(i).split(":")[1].split(","));
                    recipes.put(key, value);
                    availableDrinks.addAll(value);
                }
            }
            if (!readFile.get(i).contains("#") && !readFile.get(i).contains(":") && !readFile.get(i).isEmpty()) {
                endOfProducts = i;
                break;
            }
        }

        for (int i = endOfProducts; i < readFile.size(); i++) {
            if (!readFile.get(i).isEmpty()) {
                String[] current = readFile.get(i).split(",");
                for (int j = 0; j < current.length; j++) {
                    current[j] = current[j].trim();
                }
                availableIngredients.addAll(Arrays.asList(current));
            }
        }

        Set<String> allIngreds = new HashSet<>(recipes.keySet());
        for (int i = 0; i < availableDrinks.size(); i++) {
            String currentDrink = (String) availableDrinks.toArray()[i];
            Set<String> neededIngreeds = new HashSet<>();
            for (int j = 0; j < allIngreds.size(); j++) {
                if (recipes.get(allIngreds.toArray()[j]).contains(currentDrink)) {
                    neededIngreeds.add((String) allIngreds.toArray()[j]);
                }
            }
            if (availableIngredients.containsAll(neededIngreeds)) {
                makeable.add(currentDrink);
            }
        }
        return makeable;
    }
}

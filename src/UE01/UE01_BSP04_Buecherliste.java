package UE01;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
Deletes every duplicate from the given list of books
 */

public class UE01_BSP04_Buecherliste {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(uniqueBookTitles(new String[]{
                "Abendländischer Traum am Vormittag",
                "Nasse Freuden ohne Kritik",
                "Unser Aufbruch der Geschichte",
                "Frieden am Rand",
                "Herzen ohne Paket",
                "Die Häuser ohne Pferde",
                "Unser Aufbruch der Geschichte",
                "Die Häuser ohne Pferde",
                "Die Häuser ohne Pferde",
                "Die unglückliche Fee in der Nacht", "Druckvoller Junge mit Sturm",
                "Herzen ohne Paket",
                "Die einfühlsamen Skrupel am Bahnhof",
        })));
    }

    public static String[] uniqueBookTitles(String[] titles) {
        Set<String> newTitles = new HashSet<>();
        Collections.addAll(newTitles, titles);
        String[] outputArray = newTitles.toArray(new String[newTitles.size()]);
        Arrays.sort(outputArray);
        return outputArray;
    }
}

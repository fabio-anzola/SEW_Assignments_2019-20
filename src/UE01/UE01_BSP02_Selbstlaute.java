package UE01;

/*
Checks if the words have the same vowels in them
 */

public class UE01_BSP02_Selbstlaute {
    public static void main(String[] args) {
        System.out.println(sameVowels("Car", "All"));
        System.out.println(sameVowels("", ""));
        System.out.println(sameVowels("Computer", "Outlet"));
        System.out.println(sameVowels("Cool", "OK"));
        System.out.println(sameVowels("Mouse", "Horse"));
    }

    public static boolean sameVowels(String s1, String s2) {
        int[] vowelsString1 = {0, 0, 0, 0, 0};
        int[] vowelsString2 = {0, 0, 0, 0, 0};
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        for (int i = 0; i < s1.length(); i++) {
            char current = s1.charAt(i);
            if (current == 'a') {
                vowelsString1[0] = 1;
            }
            if (current == 'e') {
                vowelsString1[1] = 1;
            }
            if (current == 'i') {
                vowelsString1[2] = 1;
            }
            if (current == 'o') {
                vowelsString1[3] = 1;
            }
            if (current == 'u') {
                vowelsString1[4] = 1;
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            char current = s2.charAt(i);
            if (current == 'a') {
                vowelsString2[0] = 1;
            }
            if (current == 'e') {
                vowelsString2[1] = 1;
            }
            if (current == 'i') {
                vowelsString2[2] = 1;
            }
            if (current == 'o') {
                vowelsString2[3] = 1;
            }
            if (current == 'u') {
                vowelsString2[4] = 1;
            }
        }
        return vowelsString1[0] == vowelsString2[0] && vowelsString1[1] == vowelsString2[1] &&
                vowelsString1[2] == vowelsString2[2] && vowelsString1[3] == vowelsString2[3] &&
                vowelsString1[4] == vowelsString2[4];
    }
}

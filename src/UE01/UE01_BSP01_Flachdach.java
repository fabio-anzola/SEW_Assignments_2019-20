package UE01;

/*
Checks if the input-values are valid for the building regulations in Vienna
 */

public class UE01_BSP01_Flachdach {
    public static void main(String[] args) {
        System.out.println("----------");
        checkHouse(5, 6.2, 3.5);
        System.out.println("----------");
        checkHouse(7.5, 7.5, 5.5);
        System.out.println("----------");
        checkHouse(7, 7, 5.5);
        System.out.println("----------");
        checkHouse(4.5, 3, 6);
    }

    public static void checkHouse(double length, double width, double height) {
        boolean invalid = false;
        if (length < 4 && length > 8) {
            invalid = true;
        }
        if (width < 4 && length > 8) {
            invalid = true;
        }
        if (height > 5.5 || height < 2.8) {
            invalid = true;
        }
        if (invalid) {
            System.out.println("Ungültige Werte.");
            return;
        }
        if (length * width > 50) {
            System.out.println("Grundfläche ist zu groß.");
            return;
        } else {
            System.out.println("Grundfläche: " + length * width + " m2");
        }
        if (height * width * length > 265) {
            System.out.println("Volumen ist zu groß.");
            return;
        } else {
            System.out.println("Volumen: " + length * width * height + " m3");
        }
    }
}

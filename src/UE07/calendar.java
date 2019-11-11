package UE07;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

public class calendar {
    public static void main(String[] args) {
        System.out.println("B.1.");
        LocalDate a = LocalDate.of(2018, 12, 20);
        System.out.println(a.getDayOfWeek().name()); // just for testing
        System.out.println(a.getDayOfWeek().getValue()); // from 1 (Monday) to 7 (Sunday)
        System.out.println(a.getMonth().length(a.isLeapYear())); // number of days in a month
        int m = 1; //month
        System.out.println(Month.values()[m - 1].name()); // name of the month m
        System.out.println("B.2.");
        System.out.println(Arrays.deepToString(getCalenderOfMonth(2015, 9)));
        System.out.println("B.3.");
        int b3Year = 2015;
        int b3Month = 9;
        printMonth(b3Year, b3Month);
        System.out.println("B.4.");
        printYear(2019);
    }

    /**
     * Gets calendar array of a month
     *
     * @param year  int
     * @param month int
     * @return int[][]
     */
    public static int[][] getCalenderOfMonth(int year, int month) {
        LocalDate n = LocalDate.of(year, month, 1);
        int[][] out = new int[6][7];
        int wholeMonth = n.getMonth().length(n.isLeapYear());
        for (int i = 0, counter = 1; i < out.length; i++) {
            for (int j = 0; j < out[0].length; j++) {
                if (counter >= wholeMonth + 1) {
                    break;
                }
                LocalDate temp = LocalDate.of(year, month, counter);
                if ((temp.getDayOfWeek().getValue()) == j + 1) {
                    out[i][j] = counter;
                    counter++;
                }
            }
        }

        return out;
    }

    /**
     * Prints the calendar of a month
     *
     * @param year  int
     * @param month int
     */
    public static void printMonth(int year, int month) {
        String bigSpace = "   ";
        String smallSpace = " ";
        int numberOfSpace = 10 - Month.values()[month - 1].name().length() - Integer.toString(year).length() + 6;
        Locale thisRegion = new Locale("de", "AT");
        int[][] tempMonth = getCalenderOfMonth(year, month);
        int[][] thisFormattedMonth = printInStyle(tempMonth, month, year);
        //System.out.println(Arrays.deepToString(thisFormattedMonth));
        String monthName = Month.values()[month - 1].getDisplayName(TextStyle.FULL, thisRegion);
        System.out.print(bigSpace + monthName + smallSpace + year);
        for (int i = 0; i < numberOfSpace; i++) {
            System.out.print(smallSpace);
        }
        System.out.println("\n" + "So" + smallSpace + "Mo" + smallSpace + "Di" + smallSpace + "Mi" + smallSpace
                + "Do" + smallSpace + "Fr" + smallSpace + "Sa");
        for (int i = 0; i < thisFormattedMonth.length; i++) {
            for (int j = 0; j < thisFormattedMonth[0].length; j++) {
                if (thisFormattedMonth[i][j] == 0) {
                    System.out.print(bigSpace);
                } else {
                    System.out.printf("%2d" + smallSpace, thisFormattedMonth[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Styles array
     *
     * @param array int[][]
     * @param month int
     * @param year  int
     * @return int[][]
     */
    public static int[][] printInStyle(int[][] array, int month, int year) {
        LocalDate n = LocalDate.of(year, month, 1);
        int wholeMonth = n.getMonth().length(n.isLeapYear());
        int[][] thisMonth = new int[array.length][array[0].length];
        for (int i = 0, counter = 1; i < thisMonth.length; i++) {
            for (int j = 0; j < thisMonth[i].length; j++) {
                int weekLenght = 7;
                if (counter >= wholeMonth + 1) {
                    return finalizeMethod(thisMonth);
                }
                n = LocalDate.of(year, month, counter);
                if (n.getDayOfWeek().getValue() == weekLenght) {
                    counter++;
                    thisMonth[i + 1][0] = counter - 1;
                    break;
                } else {
                    int y = n.getDayOfWeek().getValue();
                    counter++;
                    thisMonth[i][y] = (counter - 1);
                    counter--;
                }
                counter++;
            }
        }
        return array; //should never arrive here    just in case
    }

    /**
     * Finalizes styling
     *
     * @param currentMonth int[][]
     * @return int[][]
     */
    public static int[][] finalizeMethod(int[][] currentMonth) {
        int weekLength = 7;
        boolean endOfLine = true;
        int[][] month = new int[6][7];
        for (int i = 0; i < weekLength; i++) {
            if (currentMonth[0][i] != 0) {
                endOfLine = false;
            }
        }
        if (!endOfLine) {
            return currentMonth;
        } else {
            System.arraycopy(currentMonth, weekLength - 6, month, weekLength - weekLength, weekLength - 2);
        }
        return month;
    }

    /**
     * Prints calendar of this year
     *
     * @param year int
     */
    public static void printYear(int year) {
        int[][][][] thisZear = new int[4][3][6][7];
        int availableMoths = 12;
        Locale thisRegion = new Locale("de", "AT");
        String small = " ";
        String en = "   ";
        String x2 = "  ";
        int large = 0;
        int loopLength = (64 - Integer.toString(year).length()) / 2;
        for (int i = 0; i < loopLength; i++) {
            System.out.print(small);
        }
        System.out.print(year);
        for (int i = 0; i < loopLength; i++) {
            System.out.print(small);
        }
        System.out.println();
        for (int i = 0, month = 1; i < thisZear.length; i++) {
            for (int j = 0; j < thisZear[0].length; j++) {
                if (availableMoths + 1 > month) {
                    month++;
                    thisZear[i][j] = printInStyle(getCalenderOfMonth(year, month - 1), month - 1, year);
                }
            }
        }
        for (int i = 0, month = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                String name = Month.values()[month - 1].getDisplayName(TextStyle.FULL, thisRegion);
                large = (20 - name.length()) / 2;
                for (int k = 0; k < large; k++) {
                    System.out.print(small);
                }
                System.out.print(name);
                for (int k = 0; k < large; k++) {
                    System.out.print(small);
                }
                if (j != 2) {
                    System.out.print(x2);
                }
                if (large * 2 + name.length() != 20) {
                    System.out.print(small);
                }
                month++;
            }
            System.out.println();
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    System.out.print("So" + small + "Mo" + small + "Di" + small + "Mi" + small
                            + "Do" + small + "Fr" + small + "Sa");
                } else {
                    System.out.print("So" + small + "Mo" + small + "Di" + small + "Mi" + small
                            + "Do" + small + "Fr" + small + "Sa" + small + small);
                }
            }
            System.out.println();
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int h = 0; h < thisZear[i][k][j].length; h++) {
                        if (thisZear[i][k][j][h] == 0) {
                            System.out.print(en);
                        } else {
                            System.out.printf("%2d ", thisZear[i][k][j][h]);
                        }
                    }
                    System.out.print(small);
                }
                System.out.println();
            }
        }
    }
}

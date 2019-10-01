package UE01;

import java.util.Arrays;

/*
Calculates certain values of the given sale statistics
 */

public class UE01_BSP03_Statistik {
    public static void main(String[] args) {
        double[] incomeArray = {624.75, 545.23, 748.5, 323, 464.25, 452.35, 668.6, 345.45, 245.65, 343.25, 543.9,
                823.45, 645.35, 425.65, 245.25, 723.34, 710.2, 620, 530, 723.45};
        System.out.println(Arrays.toString(createStatistics(incomeArray)));
    }

    public static double[] createStatistics(double[] sale) {
        double totalIncome = 0;
        double averageIncome = 0;
        double maxIncome = 0;
        double minIncome = Double.MAX_VALUE;
        for (int i = 0; i < sale.length; i++) {
            totalIncome += sale[i];
            if (sale[i] > maxIncome) {
                maxIncome = sale[i];
            }
            if (sale[i] < minIncome) {
                minIncome = sale[i];
            }
        }
        averageIncome = totalIncome / sale.length;
        double[] returnArray = {totalIncome, averageIncome, maxIncome, minIncome};
        return returnArray;
    }
}

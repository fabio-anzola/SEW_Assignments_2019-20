package UE07;

public class MagicSquare {
    public static void main(String[] args) {
        int[][] arr1 = {
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 6}
        };
        System.out.println(isMagicSquare(arr1));
    }

    /**
     * Checks if array s is a magic array
     *
     * @param s Array
     * @return boolean
     */
    public static boolean isMagicSquare(int[][] s) {
        if (s.length != s[0].length) {
            return false;
            //throw new IllegalArgumentException("Not a square!");
        }
        try {
            //upper side
            int sumSideA = 0;
            for (int i = 0; i < s.length; i++) {
                sumSideA += s[0][i];
            }
            //right side
            int sumSideB = 0;
            for (int i = 0; i < s.length; i++) {
                sumSideB += s[i][s.length - 1];
            }
            if (sumSideB != sumSideA) {
                return false;
            }
            //lower side
            int sumSideC = 0;
            for (int i = 0; i < s.length; i++) {
                sumSideC += s[s.length - 1][i];
            }
            if (sumSideC != sumSideB) {
                return false;
            }
            //left side
            int sumSideD = 0;
            for (int i = 0; i < s.length; i++) {
                sumSideD += s[i][0];
            }
            if (sumSideD != sumSideC) {
                return false;
            }
            //diagonal from left-top
            int sumDiagA = 0;
            for (int i = 0; i < s.length; i++) {
                sumDiagA += s[i][i];
            }
            if (sumDiagA != sumSideD) {
                return false;
            }
            //diagonal from right-top
            int sumDiagB = 0;
            for (int i = 0, j = s.length - 1; i < s.length; i++, j--) {
                sumDiagB += s[i][j];
            }
            return sumDiagA == sumDiagB;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}

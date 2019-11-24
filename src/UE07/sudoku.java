package UE07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class sudoku {
    public static void main(String[] args) throws IOException {
        sudokuFromString("...........5....9...4....1.2....3.5....7.....438...2......9.....1.4...6..........");
        printSudoku(sudokuFromString("...........5....9...4....1.2....3.5....7.....438...2......9.....1.4...6.........."));
        System.out.println(isValidSudoku("...3165..8..5..1...1.89724.9.1.85.2....9.1....4.263..1.5.....1.1..4.9..2..61.8..."));
        System.out.println(isValidSudoku("974236158638591742125487936316754289742918563589362417867125394253649871491873625"));
        System.out.println(isValidSudoku("..9.7...5..21..9..1...28....7...5..1..851.....5....3.......3..68........21.....87"));
        System.out.println(isValidSudoku("6.159.....9..1............4.7.314..6.24.....5..3....1...6.....3...9.2.4......16.."));
        System.out.println(isValidSudoku(".4.1..35.............2.5......4.89..26.....12.5.3....7..4...16.6....7....1..8..2."));
        testSudoku("/xxx/xxx/xxx/xxx/xxx/xxx/xxx/sudoku.txt", "/xxx/xxx/xxx/xxx/xxx/xxx/xxx/sudokuU.txt");
    }

    /**
     * Converts string to array
     *
     * @param s String
     * @return int[][]
     */
    public static int[][] sudokuFromString(String s) {
        int[][] suduk = new int[9][9];
        for (int i = 0, index = 0; i < suduk.length; i++) {
            for (int j = 0; j < suduk[0].length; j++) {
                if (s.charAt(index) == '.') {
                    suduk[i][j] = 0;
                } else {
                    suduk[i][j] = Character.getNumericValue(s.charAt(index));
                }
                index++;
            }
        }
        return suduk;
    }

    /**
     * Prints array to console
     *
     * @param sudokuToPrint int[][]
     */
    public static void printSudoku(int[][] sudokuToPrint) {
        for (int i = 0; i < sudokuToPrint.length; i++) {
            for (int j = 0; j < sudokuToPrint[0].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (sudokuToPrint[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print(sudokuToPrint[i][j]);
                }
                if (j != sudokuToPrint[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && (i + 1) != sudokuToPrint.length) {
                System.out.println("------+-------+------");
            }
        }
    }

    /**
     * Parses sudoku row to array
     *
     * @param sudoku int[][]
     * @param r      int
     * @return int[]
     */
    public static int[] getLine(int[][] sudoku, int r) {
        int[] row = new int[9];
        for (int i = 0; i < row.length; i++) {
            row[i] = sudoku[r][i];
        }
        return row;
    }

    /**
     * Parses sudoku column to array
     *
     * @param sudoku int[][]
     * @param c      int
     * @return int[]
     */
    public static int[] getColumn(int[][] sudoku, int c) {
        int[] coll = new int[9];
        for (int i = 0; i < coll.length; i++) {
            coll[i] = sudoku[i][c];
        }
        return coll;
    }

    /**
     * Parses sudoku box to array
     *
     * @param sudoku int[][]
     * @param b      int
     * @return int[]
     */
    public static int[] getBox(int[][] sudoku, int b) {
        int[] box = new int[9];
        switch (b) {
            default:
                break;
            case 1:
                box[0] = sudoku[0][0];
                box[1] = sudoku[0][1];
                box[2] = sudoku[0][2];
                box[3] = sudoku[1][0];
                box[4] = sudoku[1][1];
                box[5] = sudoku[1][2];
                box[6] = sudoku[2][0];
                box[7] = sudoku[2][1];
                box[8] = sudoku[2][2];
                break;
            case 2:
                box[0] = sudoku[0][3];
                box[1] = sudoku[0][4];
                box[2] = sudoku[0][5];
                box[3] = sudoku[1][3];
                box[4] = sudoku[1][4];
                box[5] = sudoku[1][5];
                box[6] = sudoku[2][3];
                box[7] = sudoku[2][4];
                box[8] = sudoku[2][5];
                break;
            case 3:
                box[0] = sudoku[0][6];
                box[1] = sudoku[0][7];
                box[2] = sudoku[0][8];
                box[3] = sudoku[1][6];
                box[4] = sudoku[1][7];
                box[5] = sudoku[1][8];
                box[6] = sudoku[2][6];
                box[7] = sudoku[2][7];
                box[8] = sudoku[2][8];
                break;
            case 4:
                box[0] = sudoku[3][0];
                box[1] = sudoku[3][1];
                box[2] = sudoku[3][2];
                box[3] = sudoku[4][0];
                box[4] = sudoku[4][1];
                box[5] = sudoku[4][2];
                box[6] = sudoku[5][0];
                box[7] = sudoku[5][1];
                box[8] = sudoku[5][2];
                break;
            case 5:
                box[0] = sudoku[3][3];
                box[1] = sudoku[3][4];
                box[2] = sudoku[3][5];
                box[3] = sudoku[4][3];
                box[4] = sudoku[4][4];
                box[5] = sudoku[4][5];
                box[6] = sudoku[5][3];
                box[7] = sudoku[5][4];
                box[8] = sudoku[5][5];
                break;
            case 6:
                box[0] = sudoku[3][6];
                box[1] = sudoku[3][7];
                box[2] = sudoku[3][8];
                box[3] = sudoku[4][6];
                box[4] = sudoku[4][7];
                box[5] = sudoku[4][8];
                box[6] = sudoku[5][6];
                box[7] = sudoku[5][7];
                box[8] = sudoku[5][8];
                break;
            case 7:
                box[0] = sudoku[6][0];
                box[1] = sudoku[6][1];
                box[2] = sudoku[6][2];
                box[3] = sudoku[7][0];
                box[4] = sudoku[7][1];
                box[5] = sudoku[7][2];
                box[6] = sudoku[8][0];
                box[7] = sudoku[8][1];
                box[8] = sudoku[8][2];
                break;
            case 8:
                box[0] = sudoku[6][3];
                box[1] = sudoku[6][4];
                box[2] = sudoku[6][5];
                box[3] = sudoku[7][3];
                box[4] = sudoku[7][4];
                box[5] = sudoku[7][5];
                box[6] = sudoku[8][3];
                box[7] = sudoku[8][4];
                box[8] = sudoku[8][5];
                break;
            case 9:
                box[0] = sudoku[6][6];
                box[1] = sudoku[6][7];
                box[2] = sudoku[6][8];
                box[3] = sudoku[7][6];
                box[4] = sudoku[7][7];
                box[5] = sudoku[7][8];
                box[6] = sudoku[8][6];
                box[7] = sudoku[8][7];
                box[8] = sudoku[8][8];
                break;
        }
        return box;
    }

    /**
     * Checks how often num appears in the passed array
     *
     * @param num  int
     * @param data int[]
     * @return int
     */
    public static int countNum(int num, int[] data) {
        int acc = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == num) {
                acc++;
            }
        }
        return acc;
    }

    /**
     * Checks if the passed block is a valid sudoku
     *
     * @param block int[]
     * @return boolean
     */
    public static boolean isValid(int[] block) {
        for (int i = 1; i <= 9; i++) {
            if (countNum(i, block) < 0 || countNum(i, block) > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if passed array is valid sudoku
     *
     * @param s int[][]
     * @return boolean
     */
    public static boolean isValidSudoku(int[][] s) {
        for (int i = 1; i <= 9; i++) {
            if (!isValid(getBox(s, i))) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!isValid(getLine(s, i))) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!isValid(getColumn(s, i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if passed String is valid sudoku
     *
     * @param s String
     * @return boolean
     */
    public static boolean isValidSudoku(String s) {
        return isValidSudoku(sudokuFromString(s));
    }

    /**
     * Checks sudoku from file
     *
     * @param inputFile  String
     * @param outputFile String
     */
    public static void testSudoku(String inputFile, String outputFile) {
        StringBuilder outFile = new StringBuilder();
        try {
            for (String current : Files.readAllLines(Paths.get(inputFile))) {
                outFile.append(current);
                if (isValidSudoku(current)) {
                    outFile.append(": gültig\n");
                } else {
                    outFile.append(": ungültig\n");
                }
            }
            Files.write(Paths.get(outputFile), outFile.toString().getBytes());
        } catch (IOException e) {
            System.out.println("ups" + e);
        }
    }
}

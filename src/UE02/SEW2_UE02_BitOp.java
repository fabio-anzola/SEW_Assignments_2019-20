package UE02;
// ANZOLA Fabio 2bI

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
Bit operation exercises with Lotto methods
 */

public class SEW2_UE02_BitOp {
    public static void main(String[] args) {
        long n = 0xf0;
        n = setBit(n, 30);
        System.out.println(Long.toBinaryString(n));

        n = clearBit(n, 30);
        System.out.println(Long.toBinaryString(n));

        System.out.println(hasBitNr(n, 4));

        n = 0xc96;
        System.out.println(toStringBits(n));

        n = 0xfc96;
        System.out.println(countBits(n));

        System.out.println(toStringBits(getLottoTipp()));
        System.out.println(toStringBits(getLottoTipp()));

        long[] hello = {9, 7, 5};
        System.out.println(contains(hello, 6));

        long[] a = {0xd8c, 0xc96};
        String[] sa = toSortedLottoTippsStringArray(a);
        System.out.println(Arrays.toString(sa));

        System.out.println("#################END OF TESTING##################");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many Tipps do you want to have? ");
        int askedTipps = scanner.nextInt();
        long[] unsortedTipps = getLottoTipps(askedTipps);
        System.out.println(Arrays.toString(unsortedTipps));
        System.out.println(Arrays.toString(toSortedLottoTippsStringArray(unsortedTipps)));
    }

    /*
    The function below takes a long-Number and within it sets one bit to 1.
    The bit is defined by the second parameter bitNr
     */
    public static long setBit(long n, int bitNr) {
        n = n | (1L << bitNr);
        return n;
    }

    /*
    The function below takes a long-Number and within it sets one bit to 0.
    The bit is defined by the second parameter bitNr
     */
    public static long clearBit(long n, int bitNr) {
        n = n & (~(1L << bitNr));
        return n;
    }

    /*
    The function below checks in the long-Number n if the bit specified by the second parameter
    bitNr is set to 1 or 0
     */
    public static boolean hasBitNr(long n, int bitNr) {
        n = n & (1L << bitNr);
        return !Long.toBinaryString(n).equals("0");
    }

    /*
    The function below takes a long-Number n and formats it's binary value to a
    specified format
     */
    public static String toStringBits(long n) {
        String bitString = "";
        /*
        for (int i = 0; i < Long.toBinaryString(n).length(); i++) {
            if (hasBitNr(n, i)) {
                if (i < 10) {
                    bitString += "  ";
                } else {
                    bitString += " ";
                }
                bitString += i;
            }
        }
         */
        for (int i = 0; n != 0; i++, n >>>= 1) {
            if ((n & 1) != 0) {
                bitString += String.format("%3d", i);
            }
        }

        return bitString;
    }

    /*
    Teh function below counts all set bits (bits set to 1) within the number n
     */
    public static int countBits(long n) {
        int set = 0;
        for (int i = 0; i < Long.toBinaryString(n).length(); i++) {
            if (hasBitNr(n, i)) {
                set++;
            }
        }
        return set;
    }

    /*
    The function below checks, whether the array a contains the long-Number n
     */
    public static boolean contains(long[] a, long n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == n) {
                return true;
            }
        }
        return false;
    }

    /*
    The function below generated a lotto tipp.
     */
    public static long getLottoTipp() {
        long tipp = 0b0;
        Random randomNum = new Random();
        while (true) {
            int randBitNr = randomNum.nextInt(45);
            tipp = setBit(tipp, randBitNr);
            if (countBits(tipp) == 6) {
                return tipp;
            }
        }
    }

    /*
    The function below generates multiple unique lotto tipps
     */
    public static long[] getLottoTipps(int n) {
        long SingleTipp = 0b0;
        long[] tipps = new long[n];
        Random randomNum = new Random();
        for (int i = 0; i < n; i++) {
            while (true) {
                int randBitNr = randomNum.nextInt(45);
                SingleTipp = setBit(SingleTipp, randBitNr);
                if (countBits(SingleTipp) == 6) {
                    if (!contains(tipps, SingleTipp)) {
                        tipps[i] = SingleTipp;
                        SingleTipp = 0b0;
                        break;
                    } else {
                        SingleTipp = 0b0;
                    }
                }
            }
        }
        return tipps;
    }

    /*
    The function below sorts an Array of multiple lotto tipps and outputs them
    via a String-Array
     */
    public static String[] toSortedLottoTippsStringArray(long[] tipps) {
        int lengthOfTipps = tipps.length;
        String[] lottoTipps = new String[lengthOfTipps];
        for (int i = 0; i < tipps.length; i++) {
            lottoTipps[i] = toStringBits(tipps[i]);
        }
        return lottoTipps;
    }
}

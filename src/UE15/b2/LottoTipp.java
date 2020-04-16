package UE15.b2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * The LottoTipp class
 *
 * @author fabioanzola
 */
public class LottoTipp {

    /**
     * Stores the Tip-numbers
     */
    private Set<Integer> lottoNumbers;

    /**
     * The LottoTipp Constructor
     */
    public LottoTipp() {
        Set<Integer> constructTipp = new HashSet<>();
        while (constructTipp.size() != 6) {
            constructTipp.add(getRandomNumber());
        }
        this.lottoNumbers = constructTipp;
    }

    /**
     * Creates n LottoTipps
     *
     * @param n How many to create
     * @return The Set of LottoTipps
     */
    public static Set<LottoTipp> createLottoTipps(int n) {
        Set<LottoTipp> nTips = new HashSet<>();
        while (nTips.size() != n) {
            nTips.add(new LottoTipp());
        }
        return nTips;
    }

    /**
     * Calculates the winnings for all Instances created in n seconds
     *
     * @param seconds For how many seconds to run
     * @return The statistic of all created Instances
     */
    public static int[] calcGewinne(int seconds) {
        LottoTipp winner = new LottoTipp();
        int[] stats = new int[7];
        long t = System.currentTimeMillis();
        long end = t + seconds * 1000;
        while (System.currentTimeMillis() < end) {
            stats[6 - winner.countEqualNumbers(new LottoTipp())]++;
        }
        return stats;
    }

    /**
     * Calculates the winnings for all Instances created in n seconds and outputs the result nicely
     *
     * @param seconds For how many seconds to run
     */
    public static void printCalcGewinn(int seconds) {
        if (seconds < 1) {
            System.out.println("Error - Input has to be over 1");
            return;
        }
        int[] stat = calcGewinne(seconds);
        int all = 0;
        for (int value : stat) {
            all += value;
        }
        System.out.println("Ein 0-er, 1-er, etc. kam insgesamt vor:");
        System.out.println("0-er: " + stat[0]);
        System.out.println("1-er: " + stat[1]);
        System.out.println("2-er: " + stat[2]);
        System.out.println("3-er: " + stat[3]);
        System.out.println("4-er: " + stat[4]);
        System.out.println("5-er: " + stat[5]);
        System.out.println("6-er: " + stat[6]);
        System.out.println();
        System.out.println("D.h. insgesamt ist 1 Tipp von .. Tipps notwendig, um einen 1-er, 2-er, ... zu erreichen:");
        System.out.println("0-er: 1:" + ((stat[0] != 0) ? all / stat[0] : 0));
        System.out.println("1-er: 1:" + ((stat[1] != 0) ? all / stat[1] : 0));
        System.out.println("2-er: 1:" + ((stat[2] != 0) ? all / stat[2] : 0));
        System.out.println("3-er: 1:" + ((stat[3] != 0) ? all / stat[3] : 0));
        System.out.println("4-er: 1:" + ((stat[4] != 0) ? all / stat[4] : 0));
        System.out.println("5-er: 1:" + ((stat[5] != 0) ? all / stat[5] : 0));
        System.out.println("6-er: 1:" + ((stat[6] != 0) ? all / stat[6] : 0));
    }

    /**
     * Returns a random number between 1 and 45
     *
     * @return a random int
     */
    private int getRandomNumber() {
        int[] source = new int[44];
        for (int i = 1; i < 45; i++) {
            source[i - 1] = i;
        }
        return source[(int) (Math.random() * 44)];
    }

    /**
     * For printing the LottoTipp
     *
     * @return The styled output
     */
    @Override
    public String toString() {
        Set<Integer> out = new TreeSet<>(this.lottoNumbers);
        return out.toString().replaceAll("[\\[\\]]", "");
    }

    /**
     * Compares the numbers of two LottoTipps
     *
     * @param other The LottoTipp to compare to
     * @return How many numbers are equal
     */
    public int countEqualNumbers(LottoTipp other) {
        int prev = other.lottoNumbers.size();
        other.lottoNumbers.addAll(this.lottoNumbers);
        return other.lottoNumbers.size() - prev;
    }

    /**
     * Compares two LottoTipps
     *
     * @param o The LottoTipp to compare to
     * @return If they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoTipp)) {
            return false;
        }
        LottoTipp lottoTipp = (LottoTipp) o;
        return this.countEqualNumbers(lottoTipp) == 6;
    }

    /**
     * Creates a HashCode for a LottoTipp
     *
     * @return The Hashcode of the Instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.lottoNumbers);
    }
}

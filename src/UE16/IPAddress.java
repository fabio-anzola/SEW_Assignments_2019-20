package UE16;

import java.util.Objects;

/**
 * IP Address class.
 *
 * @author fabioanzola.
 */
public class IPAddress implements Comparable<IPAddress> {
    /**
     * Default LOCALHOST address.
     */
    final static IPAddress LOCALHOST = new IPAddress("127.0.0.1");
    /**
     * Default MODEM address.
     */
    final static IPAddress MODEM = new IPAddress("10.0.0.138");
    /**
     * Attribut.
     */
    private int address;

    /**
     * Default constructor.
     */
    public IPAddress() {
        this.address = to32BitIp("127.0.0.1");
    }

    /**
     * Constructor with given IP.
     *
     * @param ipaddress int
     */
    public IPAddress(int ipaddress) {
        set(ipaddress);
    }

    /**
     * Constructor with given octets.
     *
     * @param firstOct  int
     * @param secondOct int
     * @param thirdOct  int
     * @param fourthOct int
     */
    public IPAddress(int firstOct, int secondOct, int thirdOct, int fourthOct) {
        set(firstOct, secondOct, thirdOct, fourthOct);
    }

    /**
     * Constructor with given IP (array).
     *
     * @param ipaddress int[]
     */
    public IPAddress(int[] ipaddress) {
        set(ipaddress);
    }

    /**
     * Construcor with IP address given as String.
     *
     * @param ipaddress String
     */
    public IPAddress(String ipaddress) {
        set(ipaddress);
    }

    /**
     * Sets attribute through String.
     *
     * @param ip String
     */
    public void set(String ip) {
        address = to32BitIp(ip);
    }

    /**
     * Sets attribute through int.
     *
     * @param ip int
     */
    public void set(int ip) {
        address = ip;
    }

    /**
     * Sets attribute through String int of octets.
     *
     * @param o3 int
     * @param o2 int
     * @param o1 int
     * @param o0 int
     */
    public void set(int o3, int o2, int o1, int o0) {
        address = to32BitIp(new int[]{o3, o2, o1, o0});
    }

    /**
     * Sets attribute through int[].
     *
     * @param ip int[]
     */
    public void set(int[] ip) {
        address = to32BitIp(ip);
    }

    /**
     * Gets address as int.
     *
     * @return int
     */
    public int getAsInt() {
        return this.address;
    }

    /**
     * Gets octet.
     *
     * @param num int
     * @return int
     */
    public int getOctet(int num) {
        return toIntArray(this.address)[num];
    }

    /**
     * Gets address as array.
     *
     * @return int[]
     */
    public int[] getAsArray() {
        return toIntArray(this.address);
    }

    /**
     * To String method.
     *
     * @return String
     */
    @Override
    public String toString() {
        int[] passedArr = toIntArray(this.address);
        StringBuilder outString = new StringBuilder();
        for (int i = 0; i < passedArr.length; i++) {
            outString.append(passedArr[i]);
            if (i != passedArr.length - 1) {
                outString.append('.');
            }
        }
        return outString.toString();
    }

    /**
     * Equals method.
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IPAddress)) {
            return false;
        }
        IPAddress ipAddress = (IPAddress) o;
        return address == ipAddress.address;
    }

    /**
     * Converts String IP to int.
     *
     * @param ip String
     * @return int
     */
    private int to32BitIp(String ip) {
        int ipBit = 0;
        String[] ipOctets = ip.split("[.]");
        if (ipOctets.length != 4) {
            throw new IllegalArgumentException("Check your octets");
        }
        for (String ipOctet : ipOctets) {
            ipBit = (ipBit << 8) | Integer.parseInt(ipOctet);
        }
        int[] checker = toIntArray(ipBit);
        for (int value : checker) {
            if (value < 0 || value > 255) {
                throw new IllegalArgumentException("Check your octets");
            }
        }
        return ipBit;
    }

    /**
     * Converts int[] address to int.
     *
     * @param ip int[]
     * @return int
     */
    private int to32BitIp(int[] ip) {
        int ipBit = 0;
        for (int value : ip) {
            if (value < 0 || value > 255) {
                throw new IllegalArgumentException("Check your octets");
            }
            ipBit = (ipBit << 8) | value;
        }
        return ipBit;
    }

    /**
     * Converts int IP to array.
     *
     * @param ip int
     * @return int[]
     */
    private int[] toIntArray(int ip) {
        int[] returnArray = new int[4];
        String longIP = String.format("%d.%d.%d.%d", (ip >> 24 & 0xff), (ip >> 16 & 0xff), (ip >> 8 & 0xff), (ip & 0xff));
        String[] stringArr = longIP.split("[.]");
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = Integer.parseInt(stringArr[i]);
        }
        return returnArray;
    }

    /**
     * Checks if array is passed numbers are valid.
     *
     * @param firstOct  int
     * @param secondOct int
     * @param thirdOct  int
     * @param fourthOct int
     */
    private void isValidIp(int firstOct, int secondOct, int thirdOct, int fourthOct) {
        if (firstOct == 0) {
            throw new IllegalArgumentException("Check your octets");
        }
        System.out.println(firstOct);
        System.out.println(secondOct);
        System.out.println(thirdOct);
        System.out.println(fourthOct);
        if (firstOct > 255 || secondOct > 255 || thirdOct > 255 || fourthOct > 255 || firstOct < 0
                || secondOct < 0 || thirdOct < 0 || fourthOct < 0) {
            throw new IllegalArgumentException("Check your octets");
        }
    }

    /**
     * Gets the Hashcode of the object
     *
     * @return the hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(IPAddress o) {
        return Long.compare(Integer.toUnsignedLong(this.address), Integer.toUnsignedLong(o.address));
    }
}

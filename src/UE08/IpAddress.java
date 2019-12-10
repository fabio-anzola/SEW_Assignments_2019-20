package UE08;

/**
 * IP Address class.
 * @author fabioanzola.
 */
public class IpAddress {
    /**
     * Default LOCALHOST address.
     */
    final static IpAddress LOCALHOST = new IpAddress("127.0.0.1");
    /**
     * Attribut.
     */
    private int address;

    /**
     * Default MODEM address.
     */
    final static IpAddress MODEM = new IpAddress("10.0.0.138");

    /**
     * Default constructor.
     */
    public IpAddress() {
        this.address = to32BitIp("127.0.0.1");
    }

    /**
     * Constructor with given IP.
     * @param ipaddress int
     */
    public IpAddress(int ipaddress) {
        set(ipaddress);
    }

    /**
     * Constructor with given octets.
     * @param firstOct int
     * @param secondOct int
     * @param thirdOct int
     * @param fourthOct int
     */
    public IpAddress(int firstOct, int secondOct, int thirdOct, int fourthOct) {
        set(firstOct, secondOct, thirdOct, fourthOct);
    }

    /**
     * Constructor with given IP (array).
     * @param ipaddress int[]
     */
    public IpAddress(int[] ipaddress) {
        set(ipaddress);
    }

    /**
     * Construcor with IP address given as String.
     * @param ipaddress String
     */
    public IpAddress(String ipaddress) {
        set(ipaddress);
    }

    /**
     * Sets attribute through String.
     * @param ip String
     */
    public void set(String ip) {
        address = to32BitIp(ip);
    }

    /**
     * Sets attribute through int.
     * @param ip int
     */
    public void set(int ip) {
        address = ip;
    }

    /**
     * Sets attribute through String int of octets.
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
     * @param ip int[]
     */
    public void set(int[] ip) {
        address = to32BitIp(ip);
    }

    /**
     * Gets address as int.
     * @return int
     */
    public int getAsInt() {
        return this.address;
    }

    /**
     * Gets octet.
     * @param num int
     * @return int
     */
    public int getOctet(int num) {
        return toIntArray(this.address)[num];
    }

    /**
     * Gets address as array.
     * @return int[]
     */
    public int[] getAsArray() {
        return toIntArray(this.address);
    }

    /**
     * To String method.
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
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IpAddress)) return false;
        IpAddress ipAddress = (IpAddress) o;
        return address == ipAddress.address;
    }

    /**
     * Converts String IP to int.
     * @param ip String
     * @return int
     */
    private int to32BitIp(String ip) {
        int ipBit = 0;
        String[] ipOctets = ip.split("[.]");
        if (ipOctets.length != 4) {
            throw new IllegalArgumentException("Check your octets");
        }
        for (int i = 0; i < ipOctets.length; i++) {
            ipBit = (ipBit << 8) | Integer.parseInt(ipOctets[i]);
        }
        int[] checker = toIntArray(ipBit);
        for (int i = 0; i < checker.length; i++) {
            if (checker[i] < 0 || checker[i] > 255) {
                throw new IllegalArgumentException("Check your octets");
            }
        }
        return ipBit;
    }

    /**
     * Converts int[] address to int.
     * @param ip int[]
     * @return int
     */
    private int to32BitIp(int[] ip) {
        int ipBit = 0;
        for (int i = 0; i < ip.length; i++) {
            if (ip[i] < 0 || ip[i] > 255) {
                throw new IllegalArgumentException("Check your octets");
            }
            ipBit = (ipBit << 8) | ip[i];
        }
        return ipBit;
    }

    /**
     * Converts int IP to array.
     * @param ip int
     * @return int[]
     */
    private int[] toIntArray(int ip) {
        int[] returnArray = new int[4];
        String longIP = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
        longIP = String.format("%d.%d.%d.%d", (ip >> 24 & 0xff), (ip >> 16 & 0xff), (ip >> 8 & 0xff), (ip & 0xff));
        String[] stringArr = longIP.split("[.]");
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = Integer.parseInt(stringArr[i]);
        }
        return returnArray;
    }

    /**
     * Checks if array is passed numbers are valid.
     * @param firstOct int
     * @param secondOct int
     * @param thirdOct int
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
}

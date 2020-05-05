package UE16;

/**
 * Subnet class.
 *
 * @author fabioanzola.
 */
public class Subnet {
    /**
     * Default LOCALNET address.
     */
    public final static Subnet LOCALNET = new Subnet(new IPAddress("127.0.0.0"), 8);
    /**
     * Default PRIVATENET10 address.
     */
    public final static Subnet PRIVATENET10 = new Subnet(new IPAddress("10.0.0.0"), 8);
    /**
     * Attribut.
     */
    private IPAddress addr;
    /**
     * Attribut.
     */
    private IPAddress mask;

    /**
     * Constructor with String.
     *
     * @param address String
     */
    public Subnet(String address) {
        String[] splitted = address.split("/");
        if (splitted.length != 2) {
            throw new IllegalArgumentException("Check your octets");
        }
        if (splitted[1].length() <= 2) {
            if (Integer.parseInt(splitted[1]) > 32 || Integer.parseInt(splitted[1]) < 0) {
                throw new IllegalArgumentException("Check your octets");
            }
            this.addr = new IPAddress(splitted[0]);
            if ((this.addr.getAsInt() << Integer.parseInt(splitted[1])) != 0) {
                throw new IllegalArgumentException("Check your octets");
            }
            this.mask = new IPAddress(getNetmask(Integer.parseInt(splitted[1])));
        } else {
            this.mask = new IPAddress(splitted[1]);
        }
        this.addr = new IPAddress(splitted[0]);
    }

    /**
     * Constructor with IPAddress, int.
     *
     * @param ipaddr IPAddress
     * @param cidr   int
     */
    public Subnet(IPAddress ipaddr, int cidr) {
        this.addr = ipaddr;
        this.mask = new IPAddress(getNetmask(cidr));
    }

    /**
     * Constructor with IPAddress, IPAddress.
     *
     * @param ipaddr IPAddress
     * @param mask   IPAddress
     */
    public Subnet(IPAddress ipaddr, IPAddress mask) {
        this.addr = ipaddr;
        this.mask = mask;
    }

    /**
     * Constructor with String, String.
     *
     * @param ipaddr String
     * @param mask   String
     */
    public Subnet(String ipaddr, String mask) {
        this.addr = new IPAddress(ipaddr);
        this.mask = new IPAddress(mask);
    }

    /**
     * Constructor with IPAddress.
     *
     * @param ipaddr IPAddress
     */
    public Subnet(IPAddress ipaddr) {
        this.addr = ipaddr;
        int first = this.addr.getOctet(0);
        if (first <= 127 && first >= 0) {
            //A
            this.mask = new IPAddress("255.0.0.0");
        } else if (first <= 191 && first >= 128) {
            //B
            this.mask = new IPAddress("255.255.0.0");
        } else if (first <= 223 && first >= 192) {
            //C
            this.mask = new IPAddress("255.255.255.0");
        }
    }

    /**
     * Gets Netmask.
     *
     * @return IPAddress
     */
    public IPAddress getNetMask() {
        return this.mask;
    }

    /**
     * Gets Netaddress.
     *
     * @return IPAddress
     */
    public IPAddress getNetAddress() {
        return this.addr;
    }

    /**
     * Gets Netmask as CIDR.
     *
     * @return int
     */
    public int getCidr() {
        int cidr = 0;
        String mask = Integer.toBinaryString(this.mask.getAsInt());
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '1') {
                cidr++;
            }
        }
        return cidr;
    }

    /**
     * Gets number of hosts in range.
     *
     * @return int
     */
    public int getNumberOfHosts() {
        return (int) Math.pow(2, (32 - getCidr()));
    }

    /**
     * Checks if equal.
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        try {
            return this.mask.getAsInt() == ((Subnet) o).mask.getAsInt() && this.addr.getAsInt() == ((Subnet) o).addr.getAsInt();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats to String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return (this.addr + "/" + this.mask);
    }

    /**
     * Checks if ip is inside of range.
     *
     * @param ip IPAddress
     * @return boolean
     */
    public boolean isInNetwork(IPAddress ip) {
        int checkIp = to32BitIp(ip.toString());
        int networkIp = getNetwork(to32BitIp(this.addr.toString()), getCidr());
        int netMask = getNetmask();
        return (networkIp & netMask) == (checkIp & netMask);
    }

    /**
     * Gets Brodcast Ip.
     *
     * @return IPAddress
     */
    public IPAddress getBroadcastAddress() {
        return new IPAddress(to32BitIp(addr.toString()) | (~to32BitIp(mask.toString())));
    }

    /**
     * Gets first Ip in range.
     *
     * @return IPAddress
     */
    public IPAddress getFirstIp() {
        return new IPAddress(getNetwork(to32BitIp(addr.toString()), to32BitIp(mask.toString())) + 1);
    }

    /**
     * Gets last Ip in range.
     *
     * @return IPAddress
     */
    public IPAddress getLastIp() {
        return new IPAddress(to32BitIp(addr.toString()) | (~to32BitIp(mask.toString())) - 1);
    }

    /**
     * Gets all ips in range.
     *
     * @return IPAddress[]
     */
    public IPAddress[] getAllIpsInNetwork() {
        int network = getNetwork(to32BitIp(addr.toString()), to32BitIp(mask.toString()));
        IPAddress[] ips = new IPAddress[getNumberOfHosts()];
        for (int i = 0; i < getNumberOfHosts(); i++) {
            ips[i] = new IPAddress(network + i);
        }
        if (getNumberOfHosts() == 0) {
            return new IPAddress[]{new IPAddress(network)};
        }
        return ips;
    }

    /**
     * Gets next Subnet.
     *
     * @return Subnet
     */
    public Subnet getNextSubnet() {
        final int suf32 = 32 - getCidr();
        int netId = getNetwork(to32BitIp(addr.toString()), to32BitIp(mask.toString())) >>> suf32;
        return new Subnet(new IPAddress((netId + 1) << suf32), getCidr());
    }

    /**
     * Splits the Network.
     *
     * @param n int
     * @return Subnet[]
     */
    public Subnet[] splitNet(int n) {
        return new Subnet[n];
    }

    /**
     * Gets Netmask.
     *
     * @param suffix int
     * @return int
     */
    private int getNetmask(int suffix) {
        return 0xffffffff << (32 - suffix);
    }

    /**
     * Gets Netmask.
     *
     * @return int
     */
    private int getNetmask() {
        return 0xffffffff << (32 - getCidr());
    }

    /**
     * Gets network ip.
     *
     * @param ip     int
     * @param suffix int
     * @return int
     */
    private int getNetwork(int ip, int suffix) {
        return ip & getNetmask(suffix);
    }

    /**
     * Converts to 32pit int.
     *
     * @param ip String
     * @return int
     */
    private int to32BitIp(String ip) {
        int ipBit = 0;
        String[] ipOctets = ip.split("[.]");
        for (String ipOctet : ipOctets) {
            ipBit = (ipBit << 8) | Integer.parseInt(ipOctet);
        }
        return ipBit;
    }
}
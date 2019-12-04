package UE08;

/**
 * Subnet class.
 *
 * @author fabioanzola.
 */
public class Subnet {
    /**
     * Default LOCALNET address.
     */
    final static Subnet LOCALNET = new Subnet(new IpAddress("127.0.0.0"), 8);
    /**
     * Default PRIVATENET10 address.
     */
    final static Subnet PRIVATENET10 = new Subnet(new IpAddress("10.0.0.0"), 8);
    /**
     * Attribut.
     */
    private IpAddress addr;
    /**
     * Attribut.
     */
    private IpAddress mask;

    /**
     * Constructor with String.
     *
     * @param address String
     */
    public Subnet(String address) {
        String[] splitted = address.split("/");
        if (splitted[1].length() == 2) {
            this.mask = new IpAddress(getNetmask(Integer.parseInt(splitted[1])));
        } else {
            this.mask = new IpAddress(splitted[1]);
        }
        this.addr = new IpAddress(splitted[0]);
    }

    /**
     * Constructor with IpAddress, int.
     *
     * @param ipaddr IpAddress
     * @param cidr   int
     */
    public Subnet(IpAddress ipaddr, int cidr) {
        String address = ipaddr + "/" + cidr;
        String[] splitted = address.split("/");
        if (splitted[1].length() == 2) {
            this.mask = new IpAddress(getNetmask(Integer.parseInt(splitted[1])));
        } else {
            this.mask = new IpAddress(splitted[1]);
        }
        this.addr = new IpAddress(splitted[0]);
    }

    /**
     * Constructor with IpAddress, IpAddress.
     *
     * @param ipaddr IpAddress
     * @param mask   IpAddress
     */
    public Subnet(IpAddress ipaddr, IpAddress mask) {
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
        this.addr = new IpAddress(ipaddr);
        this.mask = new IpAddress(mask);
    }

    /**
     * Constructor with IpAddress.
     *
     * @param ipaddr IpAddress
     */
    public Subnet(IpAddress ipaddr) {
        this.addr = ipaddr;
        int first = this.addr.getOctet(0);
        if (first <= 127 && first >= 0) {
            //A
            this.mask = new IpAddress("255.0.0.0");
        } else if (first <= 191 && first >= 128) {
            //B
            this.mask = new IpAddress("255.255.0.0");
        } else if (first <= 223 && first >= 192) {
            //C
            this.mask = new IpAddress("255.255.255.0");
        }
    }

    /**
     * Gets Netmask.
     *
     * @return IpAddress
     */
    public IpAddress getNetMask() {
        return this.mask;
    }

    /**
     * Gets Netaddress.
     *
     * @return IpAddress
     */
    public IpAddress getNetAddress() {
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
     * @param ip IpAddress
     * @return boolean
     */
    public boolean isInNetwork(IpAddress ip) {
        int checkIp = to32BitIp(ip.toString());
        int networkIp = getNetwork(to32BitIp(this.addr.toString()), getCidr());
        int netMask = getNetmask();
        return (networkIp & netMask) == (checkIp & netMask);
    }

    /**
     * Gets Brodcast Ip.
     *
     * @return IpAddress
     */
    public IpAddress getBroadcastAddress() {
        return new IpAddress(to32BitIp(addr.toString()) | (~to32BitIp(mask.toString())));
    }

    /**
     * Gets first Ip in range.
     *
     * @return IpAddress
     */
    public IpAddress getFirstIp() {
        return new IpAddress(getNetwork(to32BitIp(addr.toString()), to32BitIp(mask.toString())) + 1);
    }

    /**
     * Gets last Ip in range.
     *
     * @return IpAddress
     */
    public IpAddress getLastIp() {
        return new IpAddress(to32BitIp(addr.toString()) | (~to32BitIp(mask.toString())) - 1);
    }

    /**
     * Gets all ips in range.
     *
     * @return IpAddress[]
     */
    public IpAddress[] getAllIpsInNetwork() {
        int network = getNetwork(to32BitIp(addr.toString()), to32BitIp(mask.toString()));
        IpAddress[] ips = new IpAddress[getNumberOfHosts()];
        for (int i = 0; i < getNumberOfHosts(); i++) {
            ips[i] = new IpAddress(network + i);
        }
        if (getNumberOfHosts() == 0) {
            IpAddress[] lastResort = {new IpAddress(network)};
            return lastResort;
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
        return new Subnet(new IpAddress((netId + 1) << suf32), getCidr());
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
        int outMask = 0xffffffff << (32 - suffix);
        return outMask;
    }

    /**
     * Gets Netmask.
     *
     * @return int
     */
    private int getNetmask() {
        int outMask = 0xffffffff << (32 - getCidr());
        return outMask;
    }

    /**
     * Gets network ip.
     *
     * @param ip     int
     * @param suffix int
     * @return int
     */
    private int getNetwork(int ip, int suffix) {
        int network = ip & getNetmask(suffix);
        return network;
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
        for (int i = 0; i < ipOctets.length; i++) {
            ipBit = (ipBit << 8) | Integer.parseInt(ipOctets[i]);
        }
        return ipBit;
    }
}
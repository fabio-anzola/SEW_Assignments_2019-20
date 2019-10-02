package UE03;

import java.util.Arrays;

/*
# Fabio ANZOLA, 2BI
# ue03_a_IPv4Bibliothek
# Version 2.0
 */

public class IPv4Bibliothek_b {
    public static void main(String[] args) {
        System.out.println(Integer.toUnsignedLong(to32BitIp("192.168.0.1")));
        System.out.println(Integer.toUnsignedLong(to32BitIp("10.0.0.1")));
        System.out.println(Integer.toUnsignedLong(to32BitIp("127.0.0.1")));
        System.out.println();
        System.out.println(Integer.toUnsignedLong(to32BitIp(new int[]{192, 168, 0, 1})));
        System.out.println(Integer.toUnsignedLong(to32BitIp(new int[]{10, 0, 0, 1})));
        System.out.println(Integer.toUnsignedLong(to32BitIp(new int[]{127, 0, 0, 1})));
        System.out.println();
        System.out.println(getSuffix("192.169.0.1/24"));
        System.out.println(getSuffix("10.0.0.1/16"));
        System.out.println(getSuffix("127.0.0.1/30"));
        System.out.println();
        System.out.println(Integer.toBinaryString(getNetmask("192.168.0.0/16")));
        System.out.println(Integer.toBinaryString(getNetmask("192.168.0.0/24")));
        System.out.println(Integer.toBinaryString(getNetmask("192.168.0.0/32")));
        System.out.println();
        System.out.println(Integer.toHexString(getNetwork(0xc0a80a78, 16)));
        System.out.println(Integer.toHexString(getNetwork(0xc0a47a78, 24)));
        System.out.println(Integer.toHexString(getNetwork(0xa3a80a78, 32)));
        System.out.println();
        System.out.println(Integer.toHexString(getNetwork("192.168.10.120/16")));
        System.out.println(Integer.toHexString(getNetwork("10.0.0.3/30")));
        System.out.println(Integer.toHexString(getNetwork("172.0.0.0/24")));
        System.out.println();
        System.out.println(Arrays.toString(toIntArray(0xc0a80001)));
        System.out.println(Arrays.toString(toIntArray(0xc0c80401)));
        System.out.println(Arrays.toString(toIntArray(0xc0a80ff1)));
        System.out.println();
        System.out.println(toString(0xc0a80001));
        System.out.println(toString(0xc1a20001));
        System.out.println(toString(0xc0a8AA01));
        System.out.println();
        System.out.println(toString(0xc0a00001, 16));
        System.out.println(toString(0xc0aA0C01, 24));
        System.out.println(toString(0xc0a8FF01, 30));
        System.out.println();
        System.out.println(toHexString(0xc0a8_0001));
        System.out.println(toHexString(0xc0a6110D));
        System.out.println(toHexString(0xc0aAFF01));
        System.out.println();
        System.out.println(toHexString(0xc0a8F0A1, 16));
        System.out.println(toHexString(0xc0a6110D, 24));
        System.out.println(toHexString(0xc0aAFF01, 30));
        System.out.println();
        System.out.println(Arrays.toString(getNextNetworks(0x0a000000, 16, 4)));
        System.out.println(Arrays.toString(getNextNetworks(0x0a000AA0, 30, 2)));
        System.out.println(Arrays.toString(getNextNetworks(0x0aF00AA0, 24, 6)));
        System.out.println();
        System.out.println(Arrays.toString(getAllNetworksForNewSuffix(0x0a000000, 8, 10)));
        System.out.println(Arrays.toString(getAllNetworksForNewSuffix(0x1a000000, 16, 16)));
        System.out.println(Arrays.toString(getAllNetworksForNewSuffix(0x1a000000, 8, 9)));
        System.out.println();
        System.out.println(Arrays.toString(getAllIpsInNetwork(0xc0a80000, 30)));
        System.out.println(Arrays.toString(getAllIpsInNetwork(0xc0a80000, 32)));
        System.out.println(Arrays.toString(getAllIpsInNetwork(0xc0aaa000, 24)));
        System.out.println();
        System.out.println(isInNetwork("10.1.2.3", "10.0.0.0/8"));
        System.out.println(isInNetwork("10.1.2.3", "10.0.0.0/24"));
        System.out.println(isInNetwork("10.1.2.3", "10.0.0.0/32"));
    }

    /**
     * Description of the <b>to32BitIp</b> method
     *
     * @param ip The ip address passed as a String value
     * @return Returns the ip Address as a 32 Bit integer
     */
    public static int to32BitIp(String ip) {
        int ipBit = 0;
        String[] IpOctets = ip.split("[.]");
        for (int i = 0; i < IpOctets.length; i++) {
            ipBit = (ipBit << 8) | Integer.parseInt(IpOctets[i]);
        }
        return ipBit;
    }

    /**
     * Description of the <b>to32BitIp</b> method
     *
     * @param ip The ip address passed as a integer array
     * @return Returns the ip Address as a 32 Bit integer
     */
    public static int to32BitIp(int[] ip) {
        int ipBit = 0;
        for (int i = 0; i < ip.length; i++) {
            ipBit = (ipBit << 8) | ip[i];
        }
        return ipBit;
    }

    /**
     * Description of the <b>getSuffix</b> method
     *
     * @param network An ip address with the CIDR of the subnetmask
     * @return Returns the CIDR of the passed network
     */
    public static int getSuffix(String network) {
        String[] ipArr = network.split("/");
        return Integer.parseInt(ipArr[1]);
    }

    /**
     * Description of the <b>getNetmask</b> method
     *
     * @param network An ip address with the CIDR of the subnetmask
     * @return Returns the subnetmask as a integer (DDN)
     */
    public static int getNetmask(String network) {
        String[] ipArr = network.split("/");
        int intMask = Integer.parseInt(ipArr[1]);
        int outMask = 0xffffffff << (32 - intMask);
        return outMask;
    }

    /**
     * Description of the <b>getNetmask</b> method
     *
     * @param suffix The subnetmask in the CIDR notation
     * @return Returns the subnetmask as a integer (DDN)
     */
    public static int getNetmask(int suffix) {
        int outMask = 0xffffffff << (32 - suffix);
        return outMask;
    }

    /**
     * Description of the <b>getNetwork</b> method
     *
     * @param ip     The ip address of a network
     * @param suffix The subnetmask of the network in the CIDR notation
     * @return Returns the network address of the ip (network)
     */
    public static int getNetwork(int ip, int suffix) {
        int network = ip & getNetmask(suffix);
        return network;
    }

    /**
     * Description of the <b>getNetwork</b> method
     *
     * @param network The ip address of a network and the subnetmask in the CIDR notation
     * @return Returns the network address of the ip (network)
     */
    public static int getNetwork(String network) {
        String[] input = network.split("/");
        int ip32 = to32BitIp(input[0]);
        int networkAddr = ip32 & getNetmask(Integer.parseInt(input[1]));
        return networkAddr;
    }

    /**
     * Description of the <b>toIntArray</b> method
     *
     * @param ip The ip address as a 32-Bit integer
     * @return Returns ip address as an array (one octet per index)
     */
    public static int[] toIntArray(int ip) {
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
     * Description of the <b>toString</b> method
     *
     * @param ip The ip address as a 32-Bit integer
     * @return Returns the ip address in the DDN
     */
    public static String toString(int ip) {
        int[] passedArr = toIntArray(ip);
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
     * Description of the <b>toString</b> method
     *
     * @param network The ip network address as a 32-Bit integer
     * @param suffix  The subnetmask in the CIDR notation
     * @return Returns the whole networkaddress (networkID/CIDR)
     */
    public static String toString(int network, int suffix) {
        StringBuilder output = new StringBuilder(toString(network));
        output.append('/');
        output.append(suffix);
        return output.toString();
    }

    /**
     * Description of the <b>toHexString</b> method
     *
     * @param ip The ip address passed as a 32-Bit integer
     * @return Returns the ip address as hex in the DDN
     */
    public static String toHexString(int ip) {
        int[] ipArr = toIntArray(ip);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < ipArr.length; i++) {
            if (Integer.toHexString(ipArr[i]).length() < 2) {
                out.append("0");
            }
            out.append(Integer.toHexString(ipArr[i]));
            if (i != ipArr.length - 1) {
                out.append(".");
            }
        }
        return out.toString();
    }

    /**
     * Description of the <b>toHexString</b> method
     *
     * @param network The network address passed as 32-Bit integer
     * @param suffix  The subnetmask in the CIDR notation
     * @return Returns the whole network address as hex
     */
    public static String toHexString(int network, int suffix) {
        StringBuilder out = new StringBuilder(toHexString(network));
        out.append('/');
        out.append(Integer.toHexString(suffix));
        return out.toString();
    }

    /**
     * Description of the <b>getNextNetworks</b> method
     *
     * @param network The network address passed
     * @param suffix  The subnetmask as CIDR
     * @param n       The number of the networks that should be calculated
     * @return Returns the n next network addresses from network
     */
    public static int[] getNextNetworks(int network, int suffix, int n) {
        final int suf32 = 32 - suffix;
        int netId = network >>> suf32;
        int[] nextNets = new int[n];
        for (int i = 0; i < n; i++) {
            nextNets[i] = (netId + i + 1) << suf32;
        }
        return nextNets;
    }

    /**
     * Description of the <b>getAllNetworksForNewSuffix</b> method
     *
     * @param network   The network address passed
     * @param suffix    The subnetmask as CIDR
     * @param newSuffix The chnaged subnetmask as CIDR
     * @return Returns every possible network address after the subnet change
     */
    public static int[] getAllNetworksForNewSuffix(int network, int suffix, int newSuffix) {
        int newBits = newSuffix - suffix;
        if (newBits < 0) {
            newBits *= -1;
        }
        int[] ipNetworks = new int[(2 << newBits) / 2];
        int netID = (network >>> (32 - (newSuffix - newBits))) << newBits;
        for (int i = 0; i < ipNetworks.length; i++) {
            ipNetworks[i] = (netID + i) << (32 - (newBits + suffix));
        }
        return ipNetworks;
    }

    /**
     * Description of the <b>getAllIpsInNetwork</b> method
     *
     * @param network The network address passed
     * @param suffix  The subnetmask as CIDR
     * @return Returns all ip's inside of the given network
     */
    public static int[] getAllIpsInNetwork(int network, int suffix) {
        network = getNetwork(network, suffix);
        int nrOfHosts = ((32 - suffix) << 2) / 2;
        int[] ips = new int[nrOfHosts];
        for (int i = 0; i < nrOfHosts; i++) {
            ips[i] = network + i;
        }
        if (nrOfHosts == 0) {
            int[] lastResort = {network};
            return lastResort;
        }
        return ips;
    }

    /**
     * Description of the <b>isInNetwork</b> method
     *
     * @param ip      The to be checker ip address passed as String
     * @param network The network address passed as String
     * @return Returns if the to be checked ip is inside the given network
     */
    public static boolean isInNetwork(String ip, String network) {
        boolean isInNetworkBool = false;
        int checkIp = to32BitIp(ip);
        int networkIp = getNetwork(network);
        int netMask = getNetmask(network);
        if ((networkIp & netMask) == (checkIp & netMask)) {
            isInNetworkBool = true;
        }
        return isInNetworkBool;
    }
}

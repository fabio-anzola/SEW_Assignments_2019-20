package UE03;

import java.util.Arrays;

/*
# Fabio ANZOLA, 2BI
# ue03_a_IPv4Bibliothek
 */

public class IPv4Bibliothek_a {
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
    }

    /*
    The function converts an ip inputted as a string to a 32 bit integer
     */
    public static int to32BitIp(String ip) {
        int ipBit = 0;
        String[] IpOctets = ip.split("[.]");
        for (int i = 0; i < IpOctets.length; i++) {
            ipBit = (ipBit << 8) | Integer.parseInt(IpOctets[i]);
        }
        return ipBit;
    }

    /*
    The function converts an integer array (an ip address) to a 32 bit integer
     */
    public static int to32BitIp(int[] ip) {
        int ipBit = 0;
        for (int i = 0; i < ip.length; i++) {
            ipBit = (ipBit << 8) | ip[i];
        }
        return ipBit;
    }

    /*
    The function detects the network suffix from the inputted network string
     */
    public static int getSuffix(String network) {
        String[] ipArr = network.split("/");
        return Integer.parseInt(ipArr[1]);
    }

    /*
    The functions calculate the Networkmask to the given Network
     */
    public static int getNetmask(String network) {
        String[] ipArr = network.split("/");
        int intMask = Integer.parseInt(ipArr[1]);
        int outMask = 0xffffffff << (32 - intMask);
        return outMask;
    }

    public static int getNetmask(int suffix) {
        int outMask = 0xffffffff << (32 - suffix);
        return outMask;
    }

    /*
    The functions calculate the network-address of the given Network
     */
    public static int getNetwork(int ip, int suffix) {
        int network = ip & getNetmask(suffix);
        return network;
    }

    public static int getNetwork(String network) {
        String[] input = network.split("/");
        int ip32 = to32BitIp(input[0]);
        int networkAddr = ip32 & getNetmask(Integer.parseInt(input[1]));
        return networkAddr;
    }

    /*
    The function converts the given ip address to an array with each octet at one index
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


}

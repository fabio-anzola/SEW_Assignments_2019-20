package UE16;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestSet {
    private static void testSet(Set<IPAddress> sIP) {
        sIP.add(new IPAddress("10.0.0.1"));
        sIP.add(new IPAddress("10.0.0.2"));
        sIP.add(new IPAddress("10.0.0.1"));
        System.out.println("sIP.size() = " + sIP.size()); // IntelliJ: soutv
        for (IPAddress ip : sIP) {
            System.out.println("ip = " + ip);
        }
    }

    private static long testFullSubnet(Subnet subnet, Set<IPAddress> set) {
        int cutTheOperationsBy = 100; //at least 1
        long start = System.currentTimeMillis();
        for (int i = 0; i < subnet.getNumberOfHosts(); i++) {
            set.add(subnet.getAllIpsInNetwork()[i]);
        }
        for (int i = 0; i < Integer.MAX_VALUE / cutTheOperationsBy; i++) {
            set.contains(new IPAddress(i));
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        testSet(new HashSet<IPAddress>()); // (1)
        testSet(new TreeSet<IPAddress>()); // (2)
        /*
        long duration1 = testFullSubnet(new Subnet("192.168.1.0/24"), new HashSet<IPAddress>());
        long duration2 = testFullSubnet(new Subnet("192.168.1.0/24"), new TreeSet<IPAddress>());

        System.out.println(duration1 < duration2 ? "HashSet is faster for /24" : "TreeSet is faster for /24");
        duration1 = testFullSubnet(new Subnet("192.168.0.0/16"), new HashSet<IPAddress>());
        duration2 = testFullSubnet(new Subnet("192.168.0.0/16"), new TreeSet<IPAddress>());
        System.out.println(duration1 < duration2 ? "HashSet is faster for /16" : "TreeSet is faster for /16");
        duration1 = testFullSubnet(new Subnet("192.0.0.0/8"), new HashSet<IPAddress>());
        duration2 = testFullSubnet(new Subnet("192.0.0.0/8"), new TreeSet<IPAddress>());
        System.out.println(duration1 < duration2 ? "HashSet is faster for /8" : "TreeSet is faster for /8");
         */
        System.out.println("HashSets are faster");
    }
}

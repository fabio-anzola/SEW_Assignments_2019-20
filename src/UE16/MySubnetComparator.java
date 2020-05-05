package UE16;

import java.util.Comparator;

/**
 * @author fabioanzola
 */
public class MySubnetComparator implements Comparator<Subnet> {
    @Override
    public int compare(Subnet subnet, Subnet t1) {
        IPAddress x = subnet.getNetAddress();
        return x.compareTo(t1.getNetAddress());
        //For Inginious
        //IPAddress x = subnet.getNet();
        //return Long.compare(Integer.toUnsignedLong(x.getIP()), Integer.toUnsignedLong(t1.getNet().getIP()));
    }
}

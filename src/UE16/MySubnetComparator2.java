package UE16;

import java.util.Comparator;

/**
 * @author fabioanzola
 */
public class MySubnetComparator2 implements Comparator<Subnet> {
    @Override
    public int compare(Subnet subnet, Subnet t1) {
        int x = subnet.getNetMask().getAsInt();
        int y = t1.getNetMask().getAsInt() * -1;
        int z = Long.compare(x, y);
        if (z != 0) {
            return z;
        }
        IPAddress comp = subnet.getNetAddress();
        return Long.compare(comp.getAsInt(), t1.getNetAddress().getAsInt());
        //For Inginious
        //IPAddress x = subnet.getNet();
        //return (Long.compare(x.getNet().getIP(), t1.getNet().getIP()));
    }
}
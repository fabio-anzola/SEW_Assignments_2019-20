package UE09;

import java.text.DecimalFormat;

/**
 * Circuit Class
 *
 * @author fabioanzola
 */
public abstract class Circuit {
    /**
     * Gets resistance
     *
     * @return double
     */
    public abstract double getResistance();

    /**
     * To String Method
     *
     * @return String
     */
    @Override
    public String toString() {
        //return String.format("%g Ω", this.getResistance());
        DecimalFormat tmp = new DecimalFormat("#.###");
        String tmp2 = String.format("%s Ω", tmp.format(getResistance()));
        String out = toSimpleString();
        out += " = " + tmp2;
        out += "(Anzahl der Widerstände: " + getCount() + ")";
        return out;
    }

    /**
     * Counter Method
     *
     * @return int
     */
    public abstract int getCount();

    /**
     * A different String method
     *
     * @return String
     */
    public abstract String toSimpleString();
}


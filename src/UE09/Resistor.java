package UE09;

import java.text.DecimalFormat;

/**
 * Resistor class
 *
 * @author fabioanzola
 */
public class Resistor extends Circuit {
    /**
     * Resistance attribute
     */
    private double value;

    /**
     * Constructor
     *
     * @param res double
     */
    public Resistor(double res) {
        this.value = res;
    }

    /**
     * Gets resistance
     *
     * @return double
     */
    public double getResistance() {
        return this.value;
    }

    /**
     * Counter Method
     *
     * @return int
     */
    @Override
    public int getCount() {
        return 1;
    }

    /**
     * Sets value
     *
     * @param value double
     */
    protected void setValue(double value) {
        this.value = value;
    }

    /**
     * A different String method
     *
     * @return String
     */
    @Override
    public String toSimpleString() {
        DecimalFormat format = new DecimalFormat("#.###");
        String out = String.format("%s Ω", format.format(getResistance()));
        return out;
    }
}


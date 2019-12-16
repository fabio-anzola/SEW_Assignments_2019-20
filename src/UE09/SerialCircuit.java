package UE09;

/**
 * SerialCircuit class
 *
 * @author fabioanzola
 */
public class SerialCircuit extends Circuit {
    /**
     * Circuit array Attribute
     */
    private Circuit[] inputCircuits;

    /**
     * Constructor
     *
     * @param inputs Object[]
     */
    public SerialCircuit(Object... inputs) {
        Circuit[] in = new Circuit[inputs.length];
        for (int i = 0; i < in.length; i++) {
            try {
                in[i] = (Circuit) inputs[i];
            } catch (Exception e) {
                //System.out.println("Check your input!");
                in[i] = new Resistor(((Number) inputs[i]).doubleValue());
            }
        }
        this.inputCircuits = in;
    }

    @Override
    public double getResistance() {
        double res = 0;
        for (int i = 0; i < this.inputCircuits.length; i++) {
            res += this.inputCircuits[i].getResistance();
        }
        return res;
    }

    /**
     * Counter Method
     *
     * @return int
     */
    @Override
    public int getCount() {
        int count = 0;
        for (int i = 0; i < this.inputCircuits.length; i++) {
            count += this.inputCircuits[i].getCount();
        }
        return count;
    }

    /**
     * A different String method
     *
     * @return String
     */
    @Override
    public String toSimpleString() {
        String out = "(";
        for (int i = 0; i < this.inputCircuits.length; i++) {
            out += this.inputCircuits[i].toSimpleString();
            if (i == this.inputCircuits.length - 1) {
                out += ")";
            } else {
                out += " + ";
            }
        }
        return out;
    }
}


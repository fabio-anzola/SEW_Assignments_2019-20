package UE09;

/**
 * ParallelCircuit class
 *
 * @author fabioanzola
 */
public class ParallelCircuit extends Circuit {
    /**
     * Circuit array Attribute
     */
    private Circuit[] inputCircuits;

    /**
     * Constructor
     *
     * @param inputs Object[]
     */
    public ParallelCircuit(Object... inputs) {
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
        double up = 0;
        double down = 1;
        for (int i = 0; i < this.inputCircuits.length; i++) {
            up += 1 / this.inputCircuits[i].getResistance();
        }
        return down / up;
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
        String out = "[";
        for (int i = 0; i < this.inputCircuits.length; i++) {
            out += this.inputCircuits[i].toSimpleString();
            if (i == this.inputCircuits.length - 1) {
                out += "]";
            } else {
                out += " | ";
            }
        }
        return out;
    }
}

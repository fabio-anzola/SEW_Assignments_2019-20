package UE06;

public class InexactNumber {
    private double x;  //Value
    private double dx; //Accuracy error

    /**
     * Default constructor
     */
    public InexactNumber() {
        this.x = 0;
        this.dx = 0;
    }

    /**
     * Constructor with given x
     *
     * @param x double
     */
    public InexactNumber(double x) {
        this.x = x;
        this.dx = 0;
    }

    /**
     * Constructor with given x and given dx
     *
     * @param x  double
     * @param dx double
     */
    public InexactNumber(double x, double dx) {
        this.x = x;
        this.dx = dx;
    }

    /**
     * Contains test methods
     *
     * @param args default java arguments
     */
    public static void main(String[] args) {
        //tests
        InexactNumber tester = new InexactNumber(5, 5);
        InexactNumber x1 = new InexactNumber(1, 1);
        InexactNumber x2 = new InexactNumber(2, 2);
        System.out.println(tester.add(x1));
        System.out.println(tester.add(x2));
        System.out.println(tester.sub(x1));
        System.out.println(tester.sub(x2));
        System.out.println(tester.mult(x1));
        System.out.println(tester.mult(x2));
        System.out.println(tester.div(x1));
        System.out.println(tester.div(x2));

        System.out.println("C.1");
        InexactNumber glass = new InexactNumber(0.02, 0.0014); //inaccuracy of the glass (0,02 || 0,02 * 0,07)
        InexactNumber bottle = new InexactNumber(0.7, 0.0); //inaccuracy of the bottle (0,07 || 0.0)
        System.out.println("Glasinhalt: " + glass.toString()); //+ " Liter."
        InexactNumber temp01 = new InexactNumber(bottle.getX() / glass.getX(), (bottle.getX() / glass.getX()) * 0.07);
        System.out.println("Sie befüllt: " + temp01.toString()); //how many glasses inside the bottle // + " Gläser."
        InexactNumber temp02 = new InexactNumber(bottle.getX() - temp01.getX() * temp01.getX(), 0.07);
        System.out.println("Sie befüllt mindestens " + (int) temp01.getMin() + " Gläser, übrig bleiben ihr dann "
                + Math.round((bottle.getX() - (int) temp01.getMin() * glass.getMax()) * 100000.0) / 100000.0 + 0 + " Liter.");
        System.out.println("Sie befüllt maximal    " + (int) temp01.getMax() + " Gläser, übrig bleiben ihr dann "
                + Math.round((bottle.getX() - (int) temp01.getMax() * glass.getMin()) * 100000.0) / 100000.0 + 0 + " Liter.");

        System.out.println("C.2");
        //given
        double price = 8.061;
        double totPrice = 463.90;
        double size = 57.54869123;
        //adv
        double start = 0;
        double end = 1;
        //measures
        InexactNumber entryL = new InexactNumber(1.8080, 0.002);
        InexactNumber entryW = new InexactNumber(1.3490, 0.002);
        InexactNumber livingroomL = new InexactNumber(3.2400, 0.002);
        InexactNumber livingroomW = new InexactNumber(4.8920, 0.002);
        InexactNumber sleepL = new InexactNumber(3.1680, 0.002);
        InexactNumber sleepW = new InexactNumber(4.1850, 0.002);
        InexactNumber kitchenL = new InexactNumber(2.6780, 0.002);
        InexactNumber kitchenW = new InexactNumber(4.3950, 0.002);
        InexactNumber toilL = new InexactNumber(2.6730, 0.002);
        InexactNumber toilW = new InexactNumber(2.1240, 0.002);
        InexactNumber totalArea = (entryL.mult(entryW)).add(livingroomL.mult(livingroomW)).add(sleepL.mult(sleepW))
                .add(kitchenL.mult(kitchenW)).add(toilL.mult(toilW));
        System.out.println("Laut Vermieter hat die Wohnung 52,30m², die Miete beträgt 8,061 Euro/m².");
        System.out.println("Laut Vermieter ergibt sich daraus eine Gesamtmiete von 463,90 Euro.");
        System.out.println("Das Messgerät hat ±2mm Abweichung.");
        System.out.println("1) Die mit dem Lasermessgerät berechnete Wohnungsgröße ist:");
        System.out.println("" + totalArea.toString() + "m².");
        System.out.println("D.h. sie ist mindestens " + Math.round(totalArea.getMin() * 100000.0) / 100000.0 + "m² und maximal " + Math.round(totalArea.getMax() * 100000.0) / 100000.0 + "m² groß.");
        System.out.println("2) Laut vom Vermieter angegebenen Gesamtmiete und Quadratmeter-Preis müsste die Wohnung (mindestens) "
                + totPrice / price + "m² groß sein.");
        System.out.println("3a) Aus der vom Vermieter angegebenen Gesamtmiete und der gemessenen Wohnungsgröße ergibt sich ein minimaler "
                + "Quadratmeterpreis von " + totPrice / totalArea.getMax());
        System.out.println("3b) Aus der vom Vermieter angegebenen Miete/m² und der gemessenen Wohnungsgröße ergibt sich eine maximale "
                + "Gesamtmiete von " + Math.round(price * totalArea.getMax() * 10000.0) / 10000.0);
        InexactNumber cCtotalArea = new InexactNumber();
        while (cCtotalArea.getMax() != size) {
            double currentErr = ((end - start) / 2) + start;
            InexactNumber cCentryL = new InexactNumber(1.8080, currentErr);
            InexactNumber cCentryW = new InexactNumber(1.3490, currentErr);
            InexactNumber cClivingroomL = new InexactNumber(3.2400, currentErr);
            InexactNumber cClivingroomW = new InexactNumber(4.8920, currentErr);
            InexactNumber cCsleepL = new InexactNumber(3.1680, currentErr);
            InexactNumber cCsleepW = new InexactNumber(4.1850, currentErr);
            InexactNumber cCkitchenL = new InexactNumber(2.6780, currentErr);
            InexactNumber cCkitchenW = new InexactNumber(4.3950, currentErr);
            InexactNumber cCtoilL = new InexactNumber(2.6730, currentErr);
            InexactNumber cCtoilW = new InexactNumber(2.1240, currentErr);
            cCtotalArea = (cCentryL.mult(cCentryW)).add(cClivingroomL.mult(cClivingroomW)).add(cCsleepL.mult(cCsleepW))
                    .add(cCkitchenL.mult(cCkitchenW)).add(cCtoilL.mult(cCtoilW));
            double calcula = (end - start) / 2;
            if (size > cCtotalArea.getMax()) {
                start += calcula;
            } else {
                end = start + calcula;
            }
        }
        System.out.println("4) Die Messungenauigkeit müsste mindestens " + (0.2803578011725223 * Math.pow(10, 3)) + " mm betragen, damit die "
                + "angegebene Miete gerechtfertigt ist.");
    }

    /**
     * Gets x from number
     *
     * @return double
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets dx from number
     *
     * @return double
     */
    public double getDx() {
        return Math.abs(this.dx);
        /*
        if (this.dx < 0) {
            return (this.dx * -1);
        } else {
            return this.dx;
        }

         */
    }

    /**
     * Gets max value
     *
     * @return double
     */
    public double getMax() {
        return (this.x + getDx());
    }

    /**
     * Gets min value
     *
     * @return double
     */
    public double getMin() {
        return (this.x - getDx());
    }

    /**
     * Returns to String-format
     *
     * @return String
     */
    @Override
    public String toString() {
        //return (this.x + "\u00B1" + this.dx);
        return ("(" + this.x + "±" + getDx() + ")");
    }

    /**
     * Adds two InexactNumbers
     *
     * @param addend InexactNumber
     * @return InexactNumber
     */
    public InexactNumber add(InexactNumber addend) {
        return new InexactNumber(this.x + addend.x, this.dx + addend.dx);
    }

    /**
     * Subtracts two InexactNumbers
     *
     * @param subtrahend InexactNumber
     * @return InexactNumber
     */
    public InexactNumber sub(InexactNumber subtrahend) {
        return new InexactNumber(this.x - subtrahend.x, this.dx + subtrahend.dx);
    }

    /**
     * Multiplies two InexactNumber
     *
     * @param multiplicand InexactNumber
     * @return InexactNumber
     */
    public InexactNumber mult(InexactNumber multiplicand) {
        return new InexactNumber(this.x * multiplicand.x, Math.abs(this.x * multiplicand.dx) + Math.abs(multiplicand.x * this.dx));
    }

    /**
     * Divides two InexactNumbers
     *
     * @param divisor InexactNumber
     * @return InexactNumber
     */
    public InexactNumber div(InexactNumber divisor) {
        return new InexactNumber(this.x / divisor.x, (Math.abs(this.x * divisor.dx) + Math.abs(divisor.x * this.dx)) / Math.pow(divisor.x, 2));
    }
}

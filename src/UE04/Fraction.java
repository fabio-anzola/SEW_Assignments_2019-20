/*
Due to copyright I had to remove all methods given by the professors.
 */

package UE04;

public class Fraction {
    /**
     * soll aus einer double-Zahl einen möglichst passenden Bruch erzeugen
     *
     * @param decimal double number to be converted
     */
    public Fraction(double decimal) {
        String s = String.valueOf(decimal);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            decimal *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(decimal);
        this.numerator = num;
        this.denominator = denom;
    }

    /**
     * GCD = Greatest Common Divisor = größter gemeinsamer Teiler (ggT)
     *
     * @param a erste Zahl
     * @param b zweite Zahl
     * @return der groeszte gemeinsame Teiler von a und b
     */
    public static long gcd(long a, long b) {
		/* RECURSIVE
		long returner = -1;
		if (b == 0) {
			returner = a;
		}
		else {
			returner = gcd(b,a%b);
		}
		return returner;
		 */
        //NON RECURSIVE
        long result;
        if (a == 0) {
            return Math.abs(b);
        }
        if (b == 0) {
            return Math.abs(a);
        }
        do {
            result = a % b;
            a = b;
            b = result;
        } while (b != 0);
        return Math.abs(a);
    }

    @Override
    public String toString() {
        reduction();
        if (denominator == 1) {
            return Long.toString(numerator);
        }

        return numerator + "/" + denominator;
    }

    /**
     * Setzt den Zähler ({@link #numerator}) und Nenner ({@link #denominator}) des Bruchs und kürzt den Bruch.<br>
     * Tipp: eine ArithmeticException / by zero loest du am leichtesten aus mit: "numerator = numerator / denominator;"
     *
     * @param numerator   Zähler
     * @param denominator Nenner
     * @throws ArithmeticException / by zero, falls der Nenner == 0 ist
     */
    public void set(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        if (denominator > 0) {
            this.numerator = (numerator / gcd);
            this.denominator = (denominator / gcd);
        } else {
            if (denominator == 0) {
                throw new ArithmeticException("ArithmeticException / by zero");
            }
            this.numerator = (numerator / gcd);
            this.numerator *= -1;
            this.denominator = Math.abs(denominator / gcd);
        }
    }

    /**
     * Erzeugt einen negativen Bruch
     *
     * @return random nagative Fraction
     */
    public Fraction minus() {
        return new Fraction(this.getNumerator() * -1, getDenominator());
    }

    /**
     * Berechnet den Rest der Division des Bruchs durch f
     *
     * @param f Fraction
     * @return this%f
     */
    public Fraction modulo(Fraction f) {
		/*
		System.out.println(this.numerator);
		System.out.println(this.denominator);
		System.out.println(f.numerator);
		System.out.println(f.denominator);
		 */
        Fraction calculator = new Fraction();
        calculator.setNumerator((this.numerator * f.denominator) % (f.numerator * this.denominator));
        calculator.setDenominator(this.denominator * f.denominator);
        return calculator;
    }

    /**
     * Multiplikation this * f
     *
     * @param f Fraction
     * @return this * f
     */
    public Fraction mult(Fraction f) {
        Fraction calculator = new Fraction();
        calculator.setNumerator(this.numerator * f.numerator);
        calculator.setDenominator(this.denominator * f.denominator);
        return calculator;
    }

    /**
     * Erzeugt den Kehrwert des Bruchs
     *
     * @return Fraction
     */
    public Fraction reciprocal() {
        Fraction f1 = new Fraction(this.denominator, this.numerator);
        //f1.setNumerator(this.denominator);
        //f1.setDenominator(this.numerator);
        return f1;
    }

    /**
     * Subtraktion this - f
     *
     * @param f Fraction
     * @return sub of this & f
     */
    public Fraction sub(Fraction f) {
        return new Fraction(numerator * f.denominator - f.numerator * denominator,
                denominator * f.denominator);
    }

    /**
     * Wandelt den Bruch in eine double-Zahl um
     *
     * @return double
     */
    public double toDouble() {
        return ((double) this.numerator / (double) this.denominator);
    }

    /**
     * Division this / f
     *
     * @param f Fraction
     * @return devided Fraction
     */
    public Fraction div(Fraction f) {
        return this.mult(f.reciprocal());
    }

    /**
     * Reduction of fraction
     */
    public void reduction() {
        int m = Math.abs((int) this.numerator);
        int n = Math.abs((int) this.denominator);

        int r;
        do {
            r = m % n;
            m = n;
            n = r;
        } while (n != 0);

        if (m > 1) {
            this.numerator /= m;
            this.denominator /= m;
        }
    }
}

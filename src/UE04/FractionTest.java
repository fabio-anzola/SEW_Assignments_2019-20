/*
Due to copyright I had to remove all tests given by the professors.
 */

package UE04;

public class FractionTest {
    public static void main(String[] args) {
        long l1 = Fraction.gcd(100, 80); //20
        long l2 = Fraction.gcd(153, 1496); //17
        long l3 = Fraction.gcd(999, 1000); //1
        System.out.println(l1 + " " + l2 + " " + l3);
        System.out.println("---");
        Fraction f1 = new Fraction(10, 10);
        System.out.println(f1.getNumerator() + "/" + f1.getDenominator()); // 1/1
        Fraction f2 = new Fraction(10, -10);
        System.out.println(f2.getNumerator() + "/" + f2.getDenominator()); // -1/1
        System.out.println("---");
        System.out.println(new Fraction());
        System.out.println(new Fraction(17));
        System.out.println(new Fraction(20, -12));
        System.out.println("---");
        System.out.println("a)");
        System.out.println(new Fraction(1, 2).add(new Fraction(1, 6)));
        System.out.println("b)");
        System.out.println(new Fraction(1, 2).sub(new Fraction(1, 6)));
        System.out.println("c)");
        System.out.println(new Fraction(6, 5).mult(new Fraction(10, 8)));
        System.out.println("d)");
        System.out.println(new Fraction(16, 3).div(new Fraction(4, 9)));
        System.out.println("e)");
        System.out.println((new Fraction(1, 2).add(new Fraction(1, 3))).mult(new Fraction(-6, 5)));
        System.out.println("f)");
        System.out.println(new Fraction(23, 14).modulo(new Fraction(3, 7)));
        System.out.println("---");
    }
}

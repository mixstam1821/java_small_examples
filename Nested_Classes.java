class Outer {
    int outerField;

    class Inner {
        void display() {
            System.out.println("Value of outerField: " + outerField);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerField = 10;

        Outer.Inner inner = outer.new Inner();
        inner.display(); // Output: Value of outerField: 10
    }
}

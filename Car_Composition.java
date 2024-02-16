class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();
    }

    void startCar() {
        engine.start();
        System.out.println("Car started");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.startCar(); // Output: Engine started, Car started
    }
}

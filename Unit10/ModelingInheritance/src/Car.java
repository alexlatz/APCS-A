public class Car extends Vehicle {
    private int gasAmount;
    private final int gasCapacity;

    public Car(double speed, double cost, int gasCapacity) {
        super(speed, cost);
        this.gasAmount = 0;
        this.gasCapacity = gasCapacity;
    }

    public Car(double speed, double cost, int gasAmount, int gasCapacity) {
        this(speed, cost, gasCapacity);
        this.gasAmount = gasAmount;
    }

    public Car(double speed, double cost, int mileage, int gasAmount, int gasCapacity) {
        super(speed, cost, mileage);
        this.gasAmount = gasAmount;
        this.gasCapacity = gasCapacity;
    }

    @Override
    public void start() {
        if (this.gasAmount > 0) {
            if (!this.moving) {
                System.out.println("Started the car!");
                this.moving = true;
                this.gasAmount--;
            }
            else System.out.println("The car is already moving!");
        } else {
            System.out.println("The car is out of gas!");
        }
    }

    @Override
    public void stop() {
        if (this.moving) {
            System.out.println("Stopped the car!");
            this.moving = false;
        }
        else System.out.println("The car is already stopped!");
    }

    public void refillGas() {
        if (gasAmount < gasCapacity) {
            System.out.println("Refilled the gas!");
            gasAmount = gasCapacity;
        }
        else System.out.println("The car is full of gas!");
    }

}

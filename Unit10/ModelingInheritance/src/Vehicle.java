public class Vehicle {
    private final double speed;
    private final double cost;
    private int mileage;
    protected boolean moving;

    public Vehicle(double speed, double cost) {
        this.speed = speed;
        this.cost = cost;
        this.mileage = 0;
        this.moving = false;
    }

    public Vehicle(double speed, double cost, int mileage) {
        this(speed, cost);
        this.mileage = mileage;
    }

    public double timeFromDestination(double distance) {
        return distance / speed;
    }

    public void start() {
        if (!this.moving) {
            System.out.println("Started the vehicle!");
            this.moving = true;
        }
        else System.out.println("The vehicle is already moving!");
    }

    public void stop() {
        if (this.moving) {
            System.out.println("Stopped the vehicle!");
            this.moving = false;
        }
        else System.out.println("The vehicle isn't moving!");
    }

    public double getCost() {
        return this.cost;
    }

    public int getMileage() {
        return this.mileage;
    }
}

public class Carriage extends Vehicle {
    private boolean horseFed;

    public Carriage(double speed, double cost) {
        super(speed, cost);
        horseFed = true;
    }

    public Carriage(double speed, double cost, int mileage, boolean horseFed) {
        super(speed, cost, mileage);
        this.horseFed = horseFed;
    }

    public Carriage(double speed, double cost, int mileage) {
        this(speed, cost, mileage, true);
    }

    @Override
    public void start() {
        if (this.horseFed) {
            if (!this.moving) {
                System.out.println("Started driving the carriage!");
                this.moving = true;
            } else System.out.println("The carriage is already in motion!");
        } else System.out.println("The horse needs to be fed first!");
    }

    @Override
    public void stop() {
        if (this.moving) {
            System.out.println("Stopped the carriage!");
            this.moving = false;
            this.horseFed = false;
        } else System.out.println("The carriage is already stopped!");
    }

    public void feedHorse() {
        if (!this.horseFed) {
            System.out.println("Fed the horse!");
            this.horseFed = true;
        } else System.out.println("The horse is full!");
    }

}

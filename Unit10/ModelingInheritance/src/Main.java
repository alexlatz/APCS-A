public class Main {
    //Alex Latz wrote the entire project
    //Vehicle is a general class for things that cost money, have a speed, and mileage
    //Cars and Carriages are two different types of Vehicles (is-a relationship)
    //Each vehicle has a different way of dealing with stopping/starting
    //The Cars/Carriages have extra conditions to start/stop them and refueling/feeding
    public static void main(String[] args) {
        final Vehicle vehicle = new Vehicle(30, 15000, 2000);
        System.out.println("The vehicle's mileage is " + vehicle.getMileage());
        vehicle.start();
        //intentional to show already moving functionality
        vehicle.start();
        vehicle.stop();

        final Car car = new Car(60, 30000, 16);
        car.refillGas();
        car.start();
        car.stop();
        System.out.println(car.timeFromDestination(120) + " hours");

        final Carriage carriage = new Carriage(10, 1000);
        carriage.start();
        carriage.stop();
        carriage.feedHorse();
        System.out.println("The carriage cost " + carriage.getCost());

    }
}

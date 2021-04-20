import java.awt.*;

public class Zebra {
    public String getAnimalName() {
        return "zebra.png";
    }

    public Zebra(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.01) {
            return new Zebra(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

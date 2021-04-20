import java.awt.*;

public class Hyena {
    public String getAnimalName() {
        return "hyena.png";
    }

    public Hyena(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public boolean canEatAnimal(Animal a) {
        return !(a instanceof Hyena);
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.005) {
            return new Hyena(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

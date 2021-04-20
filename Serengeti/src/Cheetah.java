import java.awt.*;

public class Cheetah {
    public String getAnimalName() {
        return "cheetah.png";
    }

    public Cheetah(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public boolean canEatAnimal(Animal a) {
        return !(a instanceof Carnivore || a instanceof Zebra);
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.01) {
            return new Cheetah(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

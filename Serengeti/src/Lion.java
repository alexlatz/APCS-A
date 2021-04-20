import java.awt.*;

public class Lion {
    public String getAnimalName() {
        return "lion.png";
    }

    public Lion(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.01) {
            return new Lion(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

import java.awt.*;

public class Gazelle {
    public String getAnimalName() {
        return "gazelle.png";
    }

    public Gazelle(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.02) {
            return new Gazelle(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

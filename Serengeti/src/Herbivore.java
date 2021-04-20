import java.awt.*;

public abstract class Herbivore extends Animal {
    //TODO Add any missing attributes / instance variables

    public Herbivore(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
    }

    public boolean canEatAnimal(Animal a) {
        return false;
    }

    public void move() {
        //TODO Finish this method
    }
}

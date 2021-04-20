import java.awt.*;

public class Monkey {
    public String getAnimalName() {
        return "monkey.png";
    }

    public Monkey(int locX, int locY, Rectangle bounds) {
        super(locX, locY, bounds);
        //TODO Finish this constructor
    }

    public void move() {
        if (rand.nextDouble() < 0.01) {
            this.setSpeed(1000);
            super.move();
            super.move();
            super.move();
            this.setSpeed(5);
        } else {
            super.move();
        }
    }

    public Animal reproduceWithAnimal(Animal a) {
        if (rand.nextDouble() < 0.01) {
            return new Monkey(this.getLocX(), this.getLocY(), bounds);
        }
        return null;
    }
}

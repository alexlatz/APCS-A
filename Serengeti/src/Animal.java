import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public abstract class Animal {
    private static final String imageDirectory = "./img/";

    //TODO Add any missing attributes / instance variables

    private int sizeX, sizeY, speed, locX, locY, age, health;
    protected static Random rand = new Random();
    protected Rectangle bounds;
    private ImageIcon image;

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getHealth() {
        return health;
    }

    public Animal(int locX, int locY, Rectangle bounds) {
        this.locX = locX;
        this.locY = locY;
        this.bounds = bounds;
        this.age = 0;
        this.setImage();
    }

    public void move() {
        this.locX += rand.nextInt(speed) - (speed / 2);
        this.locY += rand.nextInt(speed) - (speed / 2);
        if (this.locX <= 0) {
            this.locX = 1;
        } else if (this.locX >= SerengetiPanel.WIDTH - this.sizeX) {
            this.locX = SerengetiPanel.WIDTH - this.sizeX - 1;
        }

        if (locY <= 0) {
            locY = 1;
        } else if (locY >= SerengetiPanel.HEIGHT - sizeY) {
            locY = SerengetiPanel.HEIGHT - sizeY - 1;
        }
        health--;
        age++;
    }

    public String getAnimalName() {
        return ".png";
    }

    protected void setImage() {
        String fileName = imageDirectory + this.getAnimalName();
        File myAnimal = new File(fileName);
        String path = myAnimal.getAbsolutePath();
        image = new ImageIcon(path);
        sizeX = image.getIconWidth();
        sizeY = image.getIconHeight();
    }

    public void draw(Graphics g) {
        image.paintIcon(null, g, locX, locY);
    }

    public boolean collidesWithAnimal(Animal a) {
        if (this.locX > a.locX + a.sizeX - 1) {
            return false;
        }
        if (a.locX > this.locX + this.sizeX - 1) {
            return false;
        }
        if (this.locY > a.locY + a.sizeY - 1) {
            return false;
        }
        return a.locY <= this.locY + this.sizeY - 1;
    }

    public boolean canReproduceWithAnimal(Animal a) {
        return this.getClass() == a.getClass();
    }

    public abstract Animal reproduceWithAnimal(Animal a);

    //TODO write an isOld method that indicates whether
    // this animal has exceeded the 500-frame age limit

    public void die() {
        health = -99;
    }

    public boolean isDead() {
        return health <= 0;
    }
}

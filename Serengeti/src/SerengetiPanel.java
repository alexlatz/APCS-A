import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SerengetiPanel extends JPanel {
    static final int WIDTH = 600, HEIGHT = 600;

    private ArrayList<Animal> animals = new ArrayList<>();
    private ControlPanel cPanel;
    private Rectangle bounds;

    /**
     * Constructor - I expect you to have better Javadoc
     * comments than this
     *
     * @param control A ControlPanel instance which
     *                is used to determine the next Animal to create
     */
    SerengetiPanel(ControlPanel control) {
        cPanel = control;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GREEN);
        bounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        addMouseListener(new clickListener());

        Timer timer = new Timer(100, new TimerListener());
        timer.start();
    }

    /**
     * Move all the animals in the Serengeti
     */
    private void moveAll() {
        for (Animal a : animals) {
            a.move();
        }
    }

    /**
     * Check to see whether any of the Animals can eat
     * the other Animals they are around. Then eats them.
     */
    private void checkFoodChain() {
        for (Animal a : animals) {
            if (a instanceof Carnivore) //Type check, returns a boolean
            {
                Carnivore c = (Carnivore) a; //Cast to Carnivore so you can call the methods
                for (Animal other : animals) {
                    if (a.collidesWithAnimal(other)) {
                        if (a != other && c.canEatAnimal(other)) //Compiler now says canEatAnimal is okay
                        {
                            c.eatAnimal(other);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     */
    private void checkReproduce() {
        int currAnimalsSize = animals.size(); // to prevent new babies from reproducing
        //you may want to add additional new baby reproduction limits
        for (int i = 0; i < currAnimalsSize; i++) {
            for (int j = i + 1; j < currAnimalsSize; j++) //don't want double reproduction
            {
                if (animals.get(i).collidesWithAnimal(animals.get(j))) {
                    if (animals.get(i).canReproduceWithAnimal(animals.get(j))) {
                        Animal baby = animals.get(i).reproduceWithAnimal(animals.get(j));
                        if (baby != null) //Only add non-null babies if there is room
                        {
                            if (animals.size() < 1000) // prevent heap-size crashes - limit # of animals
                                animals.add(baby);
                            i++; // prevent current parent from having too much fun...
                        }
                    }
                }
            }
        }
    }

    /**
     * Circle of life. Dead animals go back to the soil and
     * become food for the Zebra's. Aka remove them from the list.
     * <p>
     * Since Java Garbage collects, once the only reference to the
     * Animal is gone, it is deleted from memory.
     */
    private void checkDead() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).isDead()) {
                animals.remove(i);
                i--;    //Must now restart at that same index when checking
                //since ArrayLists resize dynamically
            }
        }
    }

    /**
     * This isn't the humane society. This is the Serengeti.
     * Old animals have to die.
     */
    private void euthanize() {
        for (Animal a : animals) {
            if (a.isOld())
                a.die();
        }
    }

    /**
     * Draw all the animals
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //Call to the super constructor to make sure
        //all of JPanel's paintComponent stuff is called first
        for (Animal a : animals) {
            a.draw(g);
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            moveAll();
            checkReproduce();
            checkFoodChain();
            euthanize();
            checkDead();
            repaint();    //essentially calls paintComponent to redraw GUI
        }
    }

    private class clickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            //Determine which type of Animal to create by asking ControlPanel
            //This information is based on the button that was last clicked
            String animalType = cPanel.getAnimalType();
            Point p = e.getPoint();
            System.out.println("You clicked to add a " + animalType);

            //Create the corresponding animal
            switch (animalType) {
                case "Zebra":
                    animals.add(new Zebra(p.x, p.y, bounds));
                    break;
                case "Gazelle":
                    animals.add(new Gazelle(p.x, p.y, bounds));
                    break;
                case "Lion":
                    animals.add(new Lion(p.x, p.y, bounds));
                    break;
                case "Hyena":
                    animals.add(new Hyena(p.x, p.y, bounds));
                    break;
                case "Monkey":
                    animals.add(new Monkey(p.x, p.y, bounds));
                    break;
                case "Cheetah":
                    animals.add(new Cheetah(p.x, p.y, bounds));
                    break;
                default:
                    // Do something here?
            }
            repaint();
        }
    }
}

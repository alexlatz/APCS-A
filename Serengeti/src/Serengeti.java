import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Serengeti {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Serengeti");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlPanel control = new ControlPanel();
        frame.add(control, BorderLayout.WEST);
        frame.add(new SerengetiPanel(control)); //defaults to CENTER
        frame.pack();
        frame.setVisible(true);
    }
}

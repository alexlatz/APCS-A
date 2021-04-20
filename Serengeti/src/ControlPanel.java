import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class ControlPanel extends JPanel{
	private JButton zebra, gazelle, lion, cheetah, hyena, monkey;
	private JLabel current;
	
	private String animalType; //This whole thing would be more efficient using Reflection
								//but I didn't want to over-complicate things for you guys
	
	public ControlPanel()
	{
		setPreferredSize(new Dimension(150, SerengetiPanel.HEIGHT));
		
		zebra = new JButton("Zebra");
		zebra.addActionListener(new ButtonListener("Zebra"));
		add(zebra);
		
		gazelle = new JButton("Gazelle");
		gazelle.addActionListener(new ButtonListener("Gazelle"));
		add(gazelle);
		
		lion = new JButton("Lion");
		lion.addActionListener(new ButtonListener("Lion"));
		add(lion);
		
		hyena = new JButton("Hyena");
		hyena.addActionListener(new ButtonListener("Hyena"));
		add(hyena);
		
		monkey = new JButton("Monkey");
		monkey.addActionListener(new ButtonListener("Monkey"));
		add(monkey);
		
		cheetah = new JButton("Cheetah");
		cheetah.addActionListener(new ButtonListener("Cheetah"));
		add(cheetah);
		
		//default starting animal
		animalType = "Zebra";
		add(new JLabel("Current Animal"));
		current = new JLabel("Zebra");
		add(current);
		
		//implement timer speed control if you feel adventurous
	}
	
	/**
	 * Invoked by SerengetiPanel to determine which Animal
	 * was chosen
	 * 
	 * @return The currently selected Animal type 
	 */
	public String getAnimalType()
	{
		return animalType;
	}
	
	public class ButtonListener implements ActionListener
	{
		private String name;
		
		public ButtonListener(String className)
		{
			name = className;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			animalType = name;
			current.setText(name);
		}
	}	

}

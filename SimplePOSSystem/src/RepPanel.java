import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReturnPanel extends JPanel
{

	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public ReturnPanel()
	{
		setLayout(new FlowLayout());

		setBackground(Color.white);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Button 2");
		JButton b3 = new JButton("Button 3");
		JButton b4 = new JButton("Button 4");
		JButton b5 = new JButton("Button 5");

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);

	}
}

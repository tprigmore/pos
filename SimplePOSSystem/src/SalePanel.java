import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalePanel extends JPanel
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public SalePanel(Inventory inventory, Drawer drawer)
	{

		setBackground(Color.white);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JLabel L1 = new JLabel("Layout Manager Demonstration");
		JLabel L2 = new JLabel("Choose a tab to see an eaxample of " + "a layout manager.");

		add(L1);
		add(L2);

	}
}

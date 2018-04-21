import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainManagerMenu
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public mainManagerMenu(String userName)
	{
		JFrame frame = new JFrame("Casher System - Welcome " + userName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Inventory inventory = new Inventory();

		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Sales", new SalePanel());
		tp.addTab("Return", new ReturnPanel());
		tp.addTab("Inventory", new InventoryPanel(inventory));
		tp.addTab("Orders", new OrderPanel());

		frame.getContentPane().add(tp);

		// In the main method:
		Insets insets = frame.getInsets();
		frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);

		frame.pack();
		frame.setVisible(true);
	}

}

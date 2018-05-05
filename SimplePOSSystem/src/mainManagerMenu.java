import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainManagerMenu
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public mainManagerMenu(String userName)
	{
		Inventory inventory = new Inventory();
		Drawer drawer = new Drawer(userName);

		JFrame frame = new CloseFrameAction(("Manager System - Welcome " + userName), inventory, drawer);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Sales", new SalePanel(inventory, drawer));
		tp.addTab("Inventory", new InventoryPanel(inventory));
		tp.addTab("Orders", new OrderPanel());
		tp.addTab("Reports", new ReportPanel(inventory, drawer));

		frame.getContentPane().add(tp);

		// In the main method:
		Insets insets = frame.getInsets();
		frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);

		frame.pack();
		frame.setVisible(true);
	}

}

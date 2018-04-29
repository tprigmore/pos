import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainCasherMenu
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public mainCasherMenu(String userName)
	{
		Inventory inventory = new Inventory();
		Drawer drawer = new Drawer(userName);

		JFrame frame = new CloseFrameAction(("Casher System - Welcome " + userName), inventory, drawer);

		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Sales", new SalePanel(inventory, drawer));
		tp.addTab("Return", new ReturnPanel());

		frame.getContentPane().add(tp);

		// In the main method:
		Insets insets = frame.getInsets();
		frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);

		frame.pack();
		frame.setVisible(true);
	}

}

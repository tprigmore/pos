
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class CloseFrameAction extends JFrame
{
	public CloseFrameAction(String str, Inventory inventory)
	{
		setTitle(str);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				inventory.writeInventoryFile();
				System.out.println("This is it - goodbye crule world");
				System.exit(0);
			}
		});
	}
}
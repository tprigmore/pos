import javax.swing.JFrame;

public class SimplePOSSystem
{

	public static void main(String[] args)
	{
		User user = new User();

		JFrame frame = new JFrame("Point-of-Sale System");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		frame.setSize(300, 210);

		LogInPanel panel = new LogInPanel(user);

		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}

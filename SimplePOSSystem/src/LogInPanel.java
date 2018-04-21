import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JPanel
{
	private User user;
	private String password;
	private String userName;

	private JCheckBox checkBox;
	private JButton loginButton;
	private JTextField userText;
	private JPasswordField passwordText;
	private boolean visibility = true;

	public LogInPanel(User user)
	{
		this.user = user;
		checkBoxListener checkBoxListener = new checkBoxListener();
		userTextListener textListener = new userTextListener();

		setLayout(null);

		JLabel welcomeLabel = new JLabel("Please enter your user name and password");
		welcomeLabel.setBounds(10, 5, 250, 25);
		add(welcomeLabel);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 35, 80, 25);
		add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 35, 160, 25);
		add(userText);
		userText.addActionListener(textListener);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 70, 80, 25);
		add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 70, 160, 25);
		add(passwordText);
		passwordText.addActionListener(textListener);

		JCheckBox checkBox = new JCheckBox("Remember Me");
		checkBox.setBounds(10, 110, 120, 25);
		add(checkBox);
		checkBox.addItemListener(new checkBoxListener());

		loginButton = new JButton("log In");
		loginButton.setBounds(180, 110, 80, 25);
		add(loginButton);
		loginButton.addActionListener(textListener);

		setPreferredSize(new Dimension(300, 150));
	}

	private class userTextListener implements ActionListener
	{
		private String userName;
		private String password;

		public void actionPerformed(ActionEvent event)
		{

			Object source = event.getSource();

			userName = userText.getText();
			password = passwordText.getText();

			if (source == loginButton)
			{
				if (user.checkUser(userName, password))
				{
					if (user.getJobDescription().equalsIgnoreCase("Cashier"))
					{
						new mainCasherMenu(userName);
					}
					else if (user.getJobDescription().equalsIgnoreCase("Manager"))
					{
						new mainManagerMenu(userName);
					}
					else
					{
						System.out.println("Problem with user's job description." + user.getJobDescription());
					}

				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					userText.setText(null);

				}

			}

		}
	}

	private class checkBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			if (checkBox.isSelected())
			{

			}

		}
	}

	public boolean getVisibility()
	{
		return visibility;
	}

}

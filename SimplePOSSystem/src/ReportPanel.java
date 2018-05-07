import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ReportPanel extends JPanel
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;
	private NumberFormat fmt = NumberFormat.getCurrencyInstance();

	private Inventory inventory;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public ReportPanel(Inventory inv, Drawer drawer)
	{
		inventory = inv;
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JLabel lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReports.setBounds(47, 38, 89, 36);
		add(lblReports);

		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInventory.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textArea.setText(inventory.reportInventory());
			}
		});

		btnInventory.setBounds(35, 91, 119, 36);
		add(btnInventory);

		JButton btnCashier = new JButton("Cashier");
		btnCashier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCashier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textArea.setText(drawer.reportDrawer());
			}
		});

		btnCashier.setBounds(35, 148, 119, 36);
		add(btnCashier);

		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textArea.setText(reportRegister());
			}
		});
		btnRegister.setBounds(35, 206, 119, 36);
		add(btnRegister);

		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textArea.setText(inventory.reportOrdered());
			}
		});
		btnOrders.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrders.setBounds(35, 270, 119, 36);
		add(btnOrders);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(185, 11, 811, 698);
		textArea.setEditable(false); // set textArea non-editable
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(216, 38, 766, 672);
		textArea.setText("This is in text area.");
		add(scroll, BorderLayout.EAST);
	}

	public String reportRegister()
	{
		String info = "";
		int register = 0;
		int year = 2018;
		int month = 0;
		int day = 0;
		double total = 0.0;
		boolean flag = true;
		String line = "";
		Double actualTotal = 0.0;

		flag = true;
		while (flag)
		{
			try
			{
				register = Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter register number here."));
				flag = false;
			}
			catch (Exception e)
			{
				flag = true;
			}
		}

		flag = true;
		while (flag)
		{
			try
			{
				year = Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter year here."));
				flag = false;
			}
			catch (Exception e)
			{
				flag = true;
			}
		}

		flag = true;
		while (flag)
		{
			try
			{
				month = Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter month number here."));
				flag = false;
			}
			catch (Exception e)
			{
				flag = true;
			}
		}

		flag = true;
		while (flag)
		{
			try
			{
				day = Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter day of month here."));
				flag = false;
			}
			catch (Exception e)
			{
				flag = true;
			}
		}

		flag = true;
		while (flag)
		{
			try
			{
				actualTotal = Double.parseDouble(JOptionPane.showInputDialog(this, "Type in actual drawer total:"));
				flag = false;
			}
			catch (Exception e)
			{
				flag = true;
			}
		}

		String userDirLocation = System.getProperty("user.dir");
		File userDir = new File(userDirLocation + "//drawer");
		// default to user directory

		File[] listOfFiles = userDir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			String regMatchStr = regSearch(listOfFiles[i].getName(), register);
			if (regMatchStr != "")
			{
				String dateMatchStr = dateSearch(regMatchStr, year, month, day);
				if (dateMatchStr != "")
				{
					String fileMatchStr = extentionSearch(dateMatchStr);
					if (fileMatchStr != "")
					{
						File fileIn = new File(userDir + "//" + fileMatchStr);
						try
						{
							Scanner scan = new Scanner(fileIn);
							// line 1
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							// line 2
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							// line 3
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							// line 4
							if (scan.hasNext())
							{

								line = scan.nextLine();
								info += line + "\n";
								total += Double.parseDouble(line.substring(8, line.length()));

							}
							// line 5
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							// line 6
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							// line 7
							if (scan.hasNext())
							{
								line = scan.nextLine();
								info += line + "\n";
							}
							while (scan.hasNext())
							{
								line = scan.nextLine();
							}

						}
						catch (IOException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}

		info += "********************************************************************************************\n";

		info += "Sum Total: " + fmt.format(total) + "   Actual Total: " + fmt.format(actualTotal) + "   Deficit: "
				+ fmt.format(total - actualTotal) + "\n";
		info += "********************************************************************************************\n";

		return info;
	}

	public String regSearch(String str, int reg)
	{
		String retstr = "";
		int index = str.indexOf("reg" + reg);
		if (index >= 0)
		{
			retstr = str.substring(index, str.length());
		}
		return retstr;
	}

	public String dateSearch(String str, int year, int month, int day)
	{
		String retstr = "";
		String date = String.format("_%d_%02d_%02d_", year, month, day);
		int index = str.indexOf(date);
		if (index >= 0)
		{
			return str;
		}
		return retstr;
	}

	public String extentionSearch(String str)
	{
		String retstr = "";
		int index = str.indexOf(".txtV");
		if (index >= 0)
		{
			System.out.println(str);
			return str;
		}
		return retstr;
	}
}

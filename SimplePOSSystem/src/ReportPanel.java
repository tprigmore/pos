import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ReportPanel extends JPanel
{
	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

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

		return info;
	}
}

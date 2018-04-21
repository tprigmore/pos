import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InventoryPanel extends JPanel
{

	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	public InventoryPanel(Inventory inventory)
	{
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// setBackground(Color.white);
		setBackground(Color.LIGHT_GRAY);

		JLabel labeltitleInventory = new JLabel("Inventory");
		labeltitleInventory.setFont(new Font("Cambria", Font.BOLD, 30));
		labeltitleInventory.setBounds(410, 14, 163, 31);
		add(labeltitleInventory);

		JLabel labelItemNumber = new JLabel("Item Number");
		labelItemNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelItemNumber.setBounds(29, 96, 163, 37);
		add(labelItemNumber);

		JLabel labelDescription = new JLabel("Decription");
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelDescription.setBounds(29, 193, 148, 37);
		add(labelDescription);

		JLabel labelQunatity = new JLabel("Quantity");
		labelQunatity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelQunatity.setBounds(29, 278, 93, 37);
		add(labelQunatity);

		JLabel labelCost = new JLabel("Cost");
		labelCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelCost.setBounds(29, 368, 83, 37);
		add(labelCost);

		JLabel labelSalePrice = new JLabel("Sale Price");
		labelSalePrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSalePrice.setBounds(29, 448, 101, 37);
		add(labelSalePrice);

		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(187, 193, 648, 37);
		add(textAreaDescription);

		JTextArea textAreaQuantity = new JTextArea();
		textAreaQuantity.setBounds(188, 278, 163, 37);
		add(textAreaQuantity);

		JTextArea textAreaCost = new JTextArea();
		textAreaCost.setBounds(188, 363, 163, 37);
		add(textAreaCost);

		JTextArea textAreaSupplier = new JTextArea();
		textAreaSupplier.setBounds(187, 533, 324, 37);
		add(textAreaSupplier);

		JTextArea textAreaSalesPrice = new JTextArea();
		textAreaSalesPrice.setBounds(187, 448, 163, 37);
		add(textAreaSalesPrice);

		JLabel labelSupplier = new JLabel("Supplier");
		labelSupplier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSupplier.setBounds(29, 533, 163, 40);
		add(labelSupplier);

		JLabel labelQuantityOnOrder = new JLabel("Quantity on Order");
		labelQuantityOnOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelQuantityOnOrder.setBounds(477, 266, 192, 54);
		add(labelQuantityOnOrder);

		JTextArea textAreaQuantityOnOrder = new JTextArea();
		textAreaQuantityOnOrder.setBounds(679, 284, 163, 37);
		add(textAreaQuantityOnOrder);

		JTextArea textAreaItemNumber = new JTextArea();
		textAreaItemNumber.setBounds(187, 97, 163, 37);
		add(textAreaItemNumber);
		textAreaItemNumber.setText(inventory.last().getItemNumber() + 1 + " ");

		JLabel lblThresholdToOrder = new JLabel("Threshold to Order");
		lblThresholdToOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThresholdToOrder.setBounds(477, 370, 192, 54);
		add(lblThresholdToOrder);

		JTextArea textAreaThreshold = new JTextArea();
		textAreaThreshold.setBounds(679, 377, 156, 37);
		add(textAreaThreshold);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.addActionListener(new ButtonListener());
		btnAdd.setBounds(29, 604, 163, 47);
		add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.addActionListener(new ButtonListener());
		btnRemove.setBounds(222, 604, 163, 47);
		add(btnRemove);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.addActionListener(new ButtonListener());
		btnUpdate.setBounds(410, 604, 163, 47);
		add(btnUpdate);

		JButton btnReturn = new JButton("Return to Menu");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.addActionListener(new ButtonListener());
		btnReturn.setBounds(663, 604, 213, 47);
		add(btnReturn);
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{

		}
	}
}

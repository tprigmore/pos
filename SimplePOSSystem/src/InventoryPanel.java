import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InventoryPanel extends JPanel
{

	public final int WIDTH = 1024;
	public final int HEIGHT = 800;

	private JTextField textAreaItemNumber;
	private JTextField textAreaDescription;
	private JTextField textAreaQuantity;
	private JTextField textAreaCost;
	private JTextField textAreaSupplier;
	private JTextField textAreaSalesPrice;
	private JTextField textAreaQuantityOnOrder;
	private JTextField textAreaThreshold;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnRemove;

	private Inventory myInventory;

	public InventoryPanel(Inventory inventory)
	{
		myInventory = inventory;

		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// setBackground(Color.white);
		setBackground(Color.LIGHT_GRAY);

		// JLabels

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

		JLabel labelSupplier = new JLabel("Supplier");
		labelSupplier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSupplier.setBounds(29, 533, 163, 40);
		add(labelSupplier);

		JLabel labelQuantityOnOrder = new JLabel("Quantity on Order");
		labelQuantityOnOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelQuantityOnOrder.setBounds(477, 266, 192, 54);
		add(labelQuantityOnOrder);

		JLabel lblThresholdToOrder = new JLabel("Threshold to Order");
		lblThresholdToOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThresholdToOrder.setBounds(477, 370, 192, 54);
		add(lblThresholdToOrder);

		// JTextAreas
		textAreaItemNumber = new JTextField();
		textAreaItemNumber.setBounds(187, 97, 163, 37);
		textAreaItemNumber.addActionListener(new TextListener());
		add(textAreaItemNumber);
		textAreaItemNumber.setText(inventory.last().getItemNumber() + 1 + " ");

		textAreaDescription = new JTextField();
		textAreaDescription.setBounds(187, 193, 648, 37);
		add(textAreaDescription);

		textAreaQuantity = new JTextField();
		textAreaQuantity.setBounds(188, 278, 163, 37);
		add(textAreaQuantity);

		textAreaCost = new JTextField();
		textAreaCost.setBounds(188, 363, 163, 37);
		add(textAreaCost);

		textAreaSupplier = new JTextField();
		textAreaSupplier.setBounds(187, 533, 324, 37);
		add(textAreaSupplier);

		textAreaSalesPrice = new JTextField();
		textAreaSalesPrice.setBounds(187, 448, 163, 37);
		add(textAreaSalesPrice);

		textAreaQuantityOnOrder = new JTextField();
		textAreaQuantityOnOrder.setBounds(679, 284, 163, 37);
		add(textAreaQuantityOnOrder);

		textAreaThreshold = new JTextField();
		textAreaThreshold.setBounds(679, 377, 156, 37);
		add(textAreaThreshold);

		// JButtons

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.addActionListener(new ButtonListener());
		btnAdd.setBounds(29, 604, 163, 47);
		add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.addActionListener(new ButtonListener());
		btnRemove.setBounds(222, 604, 163, 47);
		add(btnRemove);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.addActionListener(new ButtonListener());
		btnUpdate.setBounds(410, 604, 163, 47);
		add(btnUpdate);

	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int itemNumber = 0;
			String name = "";
			int quantity = 0;
			Double cost = 0.0;
			Double salePrice = 0.0;
			int quantityOnOrder = 0;
			int threshold = 20;
			textAreaItemNumber.setForeground(Color.black);
			textAreaQuantity.setForeground(Color.black);
			textAreaCost.setForeground(Color.black);
			textAreaSalesPrice.setForeground(Color.black);
			textAreaQuantityOnOrder.setForeground(Color.black);
			textAreaThreshold.setForeground(Color.black);
			textAreaItemNumber.setForeground(Color.black);

			Object source = event.getSource();

			if (source == btnAdd)
			{

				try
				{
					itemNumber = Integer.parseInt(textAreaItemNumber.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaItemNumber.setForeground(Color.red);
				}

				name = textAreaDescription.getText();

				try
				{
					quantity = Integer.parseInt(textAreaQuantity.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaQuantity.setForeground(Color.red);
				}

				try
				{
					cost = Double.parseDouble(textAreaCost.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaCost.setForeground(Color.red);
				}

				String supplier = textAreaSupplier.getText();

				try
				{
					salePrice = Double.parseDouble(textAreaSalesPrice.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaSalesPrice.setForeground(Color.red);
				}

				try
				{
					quantityOnOrder = Integer.parseInt(textAreaQuantityOnOrder.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaQuantityOnOrder.setForeground(Color.red);
				}

				try
				{
					threshold = Integer.parseInt(textAreaThreshold.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaThreshold.setForeground(Color.red);
				}

				myInventory.addNewItem(itemNumber, name, salePrice, cost, supplier, quantity, quantityOnOrder,
						threshold);
			}

			else if (source == btnRemove)
			{
				try
				{
					itemNumber = Integer.parseInt(textAreaItemNumber.getText());
				}
				catch (NumberFormatException e)
				{
					Toolkit.getDefaultToolkit().beep();
					textAreaItemNumber.setForeground(Color.red);
				}

				myInventory.removeItemByNumber(itemNumber);
				clearTextAreas();
			}
			else if (source == btnUpdate)
			{
			}
			else
			{
			}

		}

	}

	private class TextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{

			int itemNumber = 0;
			textAreaItemNumber.setForeground(Color.black);

			try
			{
				String text = textAreaItemNumber.getText();
				itemNumber = Integer.parseInt(text);
			}
			catch (NumberFormatException e)
			{
				Toolkit.getDefaultToolkit().beep();
				textAreaItemNumber.setForeground(Color.red);
			}

			Item item = myInventory.getItem(itemNumber);
			if (item == null)
			{
				clearTextAreas();
				int number = myInventory.last().getItemNumber() + 1;
				textAreaItemNumber.setText(Integer.toString(number));
			}
			else
			{
				setTextAreas(item);
			}

		}
	}

	private void setTextAreas(Item item)
	{
		textAreaDescription.setText(item.getName());
		textAreaQuantity.setText(Integer.toString(item.getQuantity()));
		textAreaCost.setText(Double.toString(item.getCost()));
		textAreaSupplier.setText(item.getSupplier());
		textAreaSalesPrice.setText(Double.toString(item.getSalePrice()));
		textAreaQuantityOnOrder.setText(Integer.toString(item.getQuantityOnOrder()));
		textAreaThreshold.setText(Integer.toString(item.getOrderThreshold()));
	}

	private void clearTextAreas()
	{
		textAreaItemNumber.setText(myInventory.last().getItemNumber() + 1 + " ");
		textAreaDescription.setText("");
		textAreaQuantity.setText("0");
		textAreaCost.setText("0.0");
		textAreaSupplier.setText("");
		textAreaSalesPrice.setText("0.0");
		textAreaQuantityOnOrder.setText("0");
		textAreaThreshold.setText("20");
	}

}

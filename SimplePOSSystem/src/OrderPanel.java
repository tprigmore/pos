import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OrderPanel extends JPanel
{

	private static final long serialVersionUID = -1201392657219744649L;
	public final int WIDTH = 1200;
	public final int HEIGHT = 1300;

	private Inventory inventory;
	private JTextArea textArea;
	private JTextField supplierNameTextField;
	private JTextField itemNumTextField;
	private JTextField itemDescTextField;
	private JTextField quantityTextField;
	private JTextField getItemDetailTextField;

	public OrderPanel(Inventory inventory)
	{
		initialize();

		this.inventory = inventory;

	}

	private void initialize()
	{
		setLayout(null);

		textArea = new JTextArea();
		textArea.setBackground(new Color(245, 255, 250));
		textArea.setLineWrap(true);
		textArea.setBounds(294, 6, 600, 500);
		JScrollPane scroll = new JScrollPane();
		textArea.setVisible(true);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(textArea);
		add(scroll);

		scroll.setVisible(true);

		JButton getCurrentInventoryButton = new JButton("Get current supplier inventory");
		getCurrentInventoryButton.setBounds(49, 347, 208, 67);
		add(getCurrentInventoryButton);

		getCurrentInventoryButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				NodeList<Item> currentInventory = inventory.getCurrentInventory();
				Node<Item> node = currentInventory.getHead().getNext();
				Node<Item> lastNode = currentInventory.getTail().getPrev();
				Map<String, String> supplierItemMap = new HashMap<>();
				textArea.setText("Current Supplier Inventory List \n\n");
				while (true)
				{
					Item item = node.getElement();
					String itemNumber = Integer.toString(item.getItemNumber());
					String itemDescription = item.getName();
					String supplierName = item.getSupplier();
					if (null == supplierItemMap.get(supplierName))
					{
						supplierItemMap.put(supplierName, itemNumber + ": " + itemDescription);
					}
					else
					{
						String existingEntry = supplierItemMap.get(supplierName);
						supplierItemMap.put(supplierName, existingEntry + "\n" + itemNumber + ": " + itemDescription);
					}
					if (node.getElement().getItemNumber() == lastNode.getElement().getItemNumber())
					{
						break;
					}
					else
					{
						node = node.getNext();
					}
				}
				for (Entry<String, String> entry : supplierItemMap.entrySet())
				{

					textArea.append("Supplier Name: " + entry.getKey() + "\n");
					textArea.append("Items: " + "\n");
					textArea.append(entry.getValue() + "\n\n");
				}
			}
		});

		getItemDetailTextField = new JTextField();
		getItemDetailTextField.setBounds(152, 289, 130, 26);
		add(getItemDetailTextField);
		getItemDetailTextField.setColumns(10);

		JButton getItemDetailsButton = new JButton("Get Item Details");
		getItemDetailsButton.setBounds(0, 290, 140, 26);
		add(getItemDetailsButton);

		getItemDetailsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String itemDetailText = getItemDetailTextField.getText();
				int itemDetailNumber = 0;
				if ((null != itemDetailText) && (!itemDetailText.equals("")))
				{
					try
					{
						itemDetailNumber = Integer.valueOf(itemDetailText);
					}
					catch (NumberFormatException nfe)
					{
						textArea.setText(
								"Enter item number correctly, unexpected characters entered. Expecting an integer.");
						return;
					}
					Item item = inventory.getItem(itemDetailNumber);
					textArea.removeAll();
					if (null == item)
					{
						textArea.setText("Item does not exist, place order to add the item.");
						return;
					}
					textArea.setText(item.toString());
				}
			}
		});

		JLabel supplierNameLabel = new JLabel("Supplier Name");
		supplierNameLabel.setBounds(49, 25, 91, 16);
		add(supplierNameLabel);
		supplierNameTextField = new JTextField();
		supplierNameTextField.setBounds(152, 20, 130, 26);
		add(supplierNameTextField);
		supplierNameTextField.setColumns(10);

		JLabel itemNumLabel = new JLabel("Item no.");
		itemNumLabel.setBounds(79, 67, 61, 16);
		add(itemNumLabel);
		itemNumTextField = new JTextField();
		itemNumTextField.setBounds(152, 62, 130, 26);
		add(itemNumTextField);
		itemNumTextField.setColumns(10);

		JLabel itemDescriptionLabel = new JLabel("Item Description");
		itemDescriptionLabel.setBounds(23, 105, 117, 16);
		add(itemDescriptionLabel);
		itemDescTextField = new JTextField();
		itemDescTextField.setBounds(152, 100, 130, 26);
		add(itemDescTextField);
		itemDescTextField.setColumns(10);

		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(79, 145, 61, 16);
		add(quantityLabel);
		quantityTextField = new JTextField();
		quantityTextField.setBounds(152, 140, 130, 26);
		add(quantityTextField);
		quantityTextField.setColumns(10);

		JButton autoOrder = new JButton("Auto Order");
		autoOrder.setBounds(93, 448, 117, 29);
		add(autoOrder);
		autoOrder.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				NodeList<Item> currentInventory = inventory.getCurrentInventory();
				Node<Item> node = currentInventory.getHead().getNext();
				Node<Item> lastNode = currentInventory.getTail().getPrev();
				boolean loop = true;
				while (node != lastNode)
				{
					int threshold = node.getElement().getOrderThreshold();
					int quantityOnOrder = node.getElement().getQuantityOnOrder();
					int itemNumber = node.getElement().getItemNumber();
					if (quantityOnOrder < threshold)
					{
						int quantityToOrder = threshold - quantityOnOrder;
						placeOrder(itemNumber, quantityToOrder);
					}
					node = node.getNext();
				}
			}
		});

		JButton placeOrderButton = new JButton("Place Order");
		placeOrderButton.setBounds(106, 183, 117, 29);
		add(placeOrderButton);
		placeOrderButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String supplierName = supplierNameTextField.getText();
				String itemNumber = itemNumTextField.getText();
				String itemDesc = itemDescTextField.getText();
				String quantity = quantityTextField.getText();

				if ((null == supplierName) || (supplierName.equals("")))
				{
					textArea.setText("Supplier name is missing, cannot place order!");
				}
				else if ((null == itemNumber) || (itemNumber.equals("")))
				{
					textArea.setText("Item number is missing, cannot place order!");
				}
				else if ((null == itemDesc) || (itemDesc.equals("")))
				{
					textArea.setText("Item Description is missing, cannot place order!");
				}
				else if ((null == quantity) || (quantity.equals("")))
				{
					textArea.setText("Quantity to order is missing, cannot place order!");
				}
				else
				{
					int itemNumberIntValue = 0;
					int quantityIntValue = 0;
					try
					{
						itemNumberIntValue = Integer.valueOf(itemNumber);
					}
					catch (NumberFormatException nfe)
					{
						textArea.setText(
								"Enter item number correctly, unexpected characters entered. Expecting an integer.");
						return;
					}
					try
					{
						quantityIntValue = Integer.valueOf(quantity);
					}
					catch (NumberFormatException nfe)
					{
						textArea.setText(
								"Enter item number correctly, unexpected characters entered. Expecting an integer.");
						return;
					}
					placeOrder(itemNumberIntValue, quantityIntValue);
				}
			}
		});
	}

	private void placeOrder(int itemNumber, int quantity)
	{
		NodeList<Item> currentInventory = inventory.getCurrentInventory();
		Node<Item> item = currentInventory.find(itemNumber);
		if (null == item)
		{
			textArea.setText("Item not found in inventory, add item to the inventory to be able to order.");
		}
		else
		{
			updateAsExistingItemInventory(itemNumber, quantity);
			keepTrackOfOrder(itemNumber, quantity);
		}
	}

	private void updateAsExistingItemInventory(int itemNumber, int quantity)
	{
		inventory.updateQuantity(itemNumber, quantity);
		Item item = inventory.getItem(itemNumber);
		// supplierNameTextField.setText("");
		// itemNumTextField.setText("");
		// itemDescTextField.setText("");
		// quantityTextField.setText("");
		textArea.setText(
				"Order successfully placed. Below is the updated inventory for the item.\n\n" + item.toString());
	}

	private void keepTrackOfOrder(int itemNumber, int quantity)
	{
		BufferedWriter bufferWriter = null;
		boolean loop = true;
		NodeList<Item> currentInventory = inventory.getCurrentInventory();
		Node<Item> node = currentInventory.getHead().getNext();
		while (loop)
		{
			if (node.getElement().getItemNumber() == itemNumber)
				loop = false;
			node = node.getNext();
		}
		String supplier = node.getElement().getSupplier();
		try
		{
			String mycontent = "Item number: " + itemNumber + "\n" + "Quantity: " + quantity + "\n" + "Supplier"
					+ supplier + "\n\n";
			File file = new File("/Users/karishma/Documents/workspace/pos/src/main/resources/order.txt");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			bufferWriter = new BufferedWriter(fw);
			bufferWriter.write(mycontent);
			System.out.println("File written Successfully");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				if (bufferWriter != null)
					bufferWriter.close();
			}
			catch (Exception ex)
			{
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}
	}
}

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SalePanel extends JPanel
{

	public final int WIDTH = 1024;
	public final int HEIGHT = 800;
	private ArrayList<TransactionItem> List;
	private Double amtTotal;
	private Double taxTotal;
	private Double subTotal;
	private Inventory inv;
	String printedList;
	String listOfReceipts;
	private Drawer drawer;

	JLabel TaxVariableLabel, TotalVariableLabel, SubtotalVariableLabel;

	JTextArea textAreaPrint;

	JTextField textAreaItemNumber, textAreaQuantity;

	JButton buttonAddItem, buttonRemoveItem, buttonRemoveAll, buttonCompleteOr, btnReturn;

	private NumberFormat fmt = NumberFormat.getCurrencyInstance();

	public SalePanel(Inventory inv, Drawer drawer)
	{
		this.inv = inv;
		this.drawer = drawer;
		this.listOfReceipts = "";
		refresh();
		initialize();

	}

	public void refresh()
	{
		List = new ArrayList<TransactionItem>();
		amtTotal = 0.0;
		taxTotal = 0.0;
		subTotal = 0.0;
		printedList = "";
	}

	public void addTransItem(int itemNumber, int qty)
	{
		Item item = inv.getItem(itemNumber);
		TransactionItem transTemp = new TransactionItem(item, qty);
		List.add(transTemp);
		subTotal = subTotal + item.getSalePrice() * qty;
		taxTotal = subTotal * .07;
		amtTotal = subTotal + taxTotal;
		printedList = ListToString();
	}

	public void ReturnTransItem(int itemNumber, int qty)
	{
		qty = -qty;
		Item item = inv.getItem(itemNumber);
		TransactionItem transTemp = new TransactionItem(item, qty);
		List.add(transTemp);
		subTotal = subTotal + item.getSalePrice() * qty;
		taxTotal = subTotal * .07;
		amtTotal = subTotal + taxTotal;
		printedList = ListToString();
	}

	public void removeTransItem(int itemNumber)
	{
		Item item = inv.getItem(itemNumber);
		double price = 0;
		double qty = 0;
		double tempSub = 0;
		for (int i = 0; i < List.size(); i++)
		{
			if (item.getItemNumber() == (List.get(i).getItem().getItemNumber()))
			{
				price = List.get(i).getItem().getSalePrice();
				qty = List.get(i).getQty();
				tempSub = price * qty;
				subTotal = subTotal - tempSub;
				taxTotal = taxTotal - tempSub * .07;
				amtTotal = subTotal + taxTotal;
				List.remove(i);
				i--;
			}
		}

		printedList = ListToString();
	}

	public void updateInventory()
	{
		int tempItemNum;
		int tempQty;
		for (int i = 0; i < List.size(); i++)
		{
			tempItemNum = List.get(i).getItem().getItemNumber();
			tempQty = List.get(i).getQty();
			inv.updateQuantity(tempItemNum, tempQty);
		}
	}

	public String ListToString()
	{
		String result = String.format("%-15s%-13s%-15s%-1s", "Item Number", "Item Name", "Qty & Price", "Item Subtotal")
				+ "\n";

		for (int i = 0; i < List.size(); i++)
		{
			result = result + List.get(i).toString() + "\n";
		}

		return result;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{

		setBounds(0, 0, WIDTH, HEIGHT);
		setLayout(null);

		// Labels
		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(467, 5, 50, 29);
		lblSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblSale);

		JLabel labelItemNumber = new JLabel("Item Number");
		labelItemNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelItemNumber.setBounds(646, 59, 139, 29);
		add(labelItemNumber);

		JLabel labelQuantity = new JLabel("Quantity");
		labelQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelQuantity.setBounds(646, 111, 84, 29);
		add(labelQuantity);

		JLabel labelSubTotal = new JLabel("Subtotal:");
		labelSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSubTotal.setBounds(33, 520, 77, 29);
		add(labelSubTotal);

		JLabel labelTax = new JLabel("Total Tax:");
		labelTax.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelTax.setBounds(33, 560, 84, 29);
		add(labelTax);

		JLabel labelTotal = new JLabel("Total Amount:");
		labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelTotal.setBounds(33, 598, 114, 29);
		add(labelTotal);

		SubtotalVariableLabel = new JLabel(subTotal.toString());
		SubtotalVariableLabel.setBounds(113, 530, 46, 14);
		add(SubtotalVariableLabel);

		TaxVariableLabel = new JLabel(taxTotal.toString());
		TaxVariableLabel.setBounds(127, 570, 46, 14);
		add(TaxVariableLabel);

		TotalVariableLabel = new JLabel(amtTotal.toString());
		TotalVariableLabel.setBounds(157, 608, 46, 14);
		add(TotalVariableLabel);

		// Text Fields

		textAreaItemNumber = new JTextField();
		textAreaItemNumber.setBounds(795, 62, 120, 29);
		add(textAreaItemNumber);

		textAreaQuantity = new JTextField();
		textAreaQuantity.setBounds(795, 114, 120, 29);
		add(textAreaQuantity);

		// Text area
		textAreaPrint = new JTextArea(25, 40);
		textAreaPrint.setLocation(20, 52);
		textAreaPrint.setSize(544, 436);
		add(textAreaPrint);
		textAreaPrint.setFont(new Font("Courier New", Font.PLAIN, 15));
		textAreaPrint.setText(
				String.format("%-15s%-13s%-15s%-1s", "Item Number", "Item Name", "Qty & Price", "Item Subtotal")
						+ "\n");

		// Buttons
		buttonAddItem = new JButton("Add Item");
		buttonAddItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonAddItem.addActionListener(new AddItemListener());
		buttonAddItem.setBounds(646, 172, 321, 65);
		add(buttonAddItem);

		buttonRemoveItem = new JButton("Remove Item");
		buttonRemoveItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonRemoveItem.addActionListener(new RemoveItemListener());
		buttonRemoveItem.setBounds(646, 267, 321, 65);
		add(buttonRemoveItem);

		buttonRemoveAll = new JButton("Remove All");
		buttonRemoveAll.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonRemoveAll.addActionListener(new RemoveAllListener());
		buttonRemoveAll.setBounds(646, 542, 321, 65);
		add(buttonRemoveAll);

		buttonCompleteOr = new JButton("Complete Order");
		buttonCompleteOr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonCompleteOr.addActionListener(new CompleteOrListener());
		buttonCompleteOr.setBounds(646, 356, 321, 65);
		add(buttonCompleteOr);

		btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReturn.addActionListener(new ReturnListener());
		btnReturn.setBounds(646, 450, 321, 65);
		add(btnReturn);

	}

	private class AddItemListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{

			if (textAreaItemNumber.getText().equals("") || textAreaQuantity.getText().equals(""))
			{
				System.out.println("Enter item number and quantity.");
			}
			else
			{

				Integer itemNum = Integer.parseInt(textAreaItemNumber.getText());
				Integer quantity = Integer.parseInt(textAreaQuantity.getText());

				addTransItem(itemNum, quantity);

				textAreaPrint.setText(ListToString());
				TotalVariableLabel.setText(fmt.format(amtTotal));
				TaxVariableLabel.setText(fmt.format(taxTotal));
				SubtotalVariableLabel.setText(fmt.format(subTotal));

			}

		}
	}

	private class ReturnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{

			if (textAreaItemNumber.getText().equals("") || textAreaQuantity.getText().equals(""))
			{
				System.out.println("Enter item number and quantity.");
			}
			else
			{

				Integer itemNum = Integer.parseInt(textAreaItemNumber.getText());
				Integer quantity = Integer.parseInt(textAreaQuantity.getText());

				ReturnTransItem(itemNum, quantity);

				textAreaPrint.setText(ListToString());
				TotalVariableLabel.setText(fmt.format(amtTotal));
				TaxVariableLabel.setText(fmt.format(taxTotal));
				SubtotalVariableLabel.setText(fmt.format(subTotal));

			}

		}
	}

	private class RemoveItemListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Integer itemNum = Integer.parseInt(textAreaItemNumber.getText());

			removeTransItem(itemNum);

			textAreaPrint.setText(ListToString());
			TotalVariableLabel.setText(fmt.format(amtTotal));
			TaxVariableLabel.setText(fmt.format(taxTotal));
			SubtotalVariableLabel.setText(fmt.format(subTotal));

		}
	}

	private class RemoveAllListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			refresh();

			textAreaPrint.setText(ListToString());
			TotalVariableLabel.setText(fmt.format(amtTotal));
			TaxVariableLabel.setText(fmt.format(taxTotal));
			SubtotalVariableLabel.setText(fmt.format(subTotal));

		}
	}

	private class CompleteOrListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String userName = drawer.username;
			Receipt receipt = new Receipt(userName, List, amtTotal, taxTotal, subTotal);
			drawer.addReciept(receipt);
			drawer.processTransaction(amtTotal);
			updateInventory();

			refresh();

			textAreaPrint.setText(ListToString());
			TotalVariableLabel.setText(fmt.format(amtTotal));
			TaxVariableLabel.setText(fmt.format(taxTotal));
			SubtotalVariableLabel.setText(fmt.format(subTotal));

		}
	}

	private void costPanel(JPanel panelMain)
	{
	}

	private void descriptionPanel(JPanel panelMain)
	{

	}

	private void itemPanel(JPanel panelMain)
	{
	}

	private void textAreaInPanel(JPanel panel)
	{

	}
}

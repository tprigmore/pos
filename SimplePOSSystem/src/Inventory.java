import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Inventory
{
	private Tree<Item> inventory;
	final String FILE = "Inventory.txt";
	private String bigString = "";
	private String orderString = "";

	public Inventory()
	{
		this.inventory = new Tree<Item>();

		String line;
		Scanner lineScan, fileScanIn;

		try
		{
			fileScanIn = new Scanner(new File(FILE));
			// Read and process each line of the file
			while (fileScanIn.hasNext())
			{
				Item item = new Item();

				line = fileScanIn.nextLine();

				lineScan = new Scanner(line);
				lineScan.useDelimiter(",");

				item.setItemNumber(Integer.parseInt(lineScan.next()));
				item.setName(lineScan.next());
				item.setSalePrice(Double.parseDouble(lineScan.next()));
				item.setCost(Double.parseDouble(lineScan.next()));
				item.setSupplier(lineScan.next());
				item.setQuantity(Integer.parseInt(lineScan.next()));
				item.setQuantityOnOrder(Integer.parseInt(lineScan.next()));
				item.setOrderThreshold(Integer.parseInt(lineScan.next()));
				this.inventory.insert(item);
			}
			fileScanIn.close();
		}
		catch (IOException e)
		{
			System.out.println("Problem opening Inventory.txt");
		}
	}

	public void writeInventoryFile()
	{
		String userDirLocation = System.getProperty("user.dir");
		File userDir = new File(userDirLocation);

		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd'_'HH_mm");
		Date dNow = new Date();
		String tempDate = ft.format(dNow);

		FileWriter fileOut;
		File fin = new File(userDir + "//" + FILE);
		File fout = new File(userDir + "//inventory//" + "Inventory" + tempDate + ".txt");
		try
		{
			Files.copy(fin.toPath(), fout.toPath());
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//
		// // If FILE exists, rename it FILE + _date_time.bak
		// boolean isFileRenamed = f.renameTo(fbak);
		//
		// if (isFileRenamed)
		// {
		// // System.out.println("Inventory bak up created.");
		// }
		// else
		// {
		// System.out.println("Error renaming the file");
		// }

		try
		{
			fileOut = new FileWriter(FILE);

			treeTraversal(fileOut, inventory.getRoot());

			fileOut.close();
		}
		catch (IOException e)
		{
			System.out.println("problem opening output file.");
		}
	}

	public Node<Item> treeTraversal(FileWriter fileOut, Node<Item> node)
	{

		if (node != null)
		{
			treeTraversal(fileOut, node.getLeft());
			writeNode2File(fileOut, node);
			treeTraversal(fileOut, node.getRight());
			return node;
		}
		else
		{
			return null;
		}

	}

	public void writeNode2File(FileWriter fileOut, Node<Item> node)
	{
		try
		{
			String stringOut = "";
			stringOut += Integer.toString((node.getElement().getItemNumber()));
			stringOut += ",";
			stringOut += node.getElement().getName();
			stringOut += ",";
			stringOut += Double.toString((node.getElement().getSalePrice()));
			stringOut += ",";
			stringOut += Double.toString((node.getElement().getCost()));
			stringOut += ",";
			stringOut += node.getElement().getSupplier();
			stringOut += ",";
			stringOut += Integer.toString((node.getElement().getQuantity()));
			stringOut += ",";
			stringOut += Integer.toString((node.getElement().getQuantityOnOrder()));
			stringOut += ",";
			stringOut += Integer.toString((node.getElement().getOrderThreshold()));
			stringOut += "\n";

			fileOut.write(stringOut);
		}
		catch (IOException e)
		{
			System.out.println("problem opening output file.");
		}

	}

	public void addItemLast(Item item)
	{
		this.inventory.insert(item);
	}

	public void addNewItem(int num, String Name, Double SalePrice, Double Cost, String Supplier, int Quantity,
			int QuantityOnOrder, int Threshold)
	{
		Item item = new Item();
		item.setItemNumber(num);
		item.setName(Name);
		item.setSalePrice(SalePrice);
		item.setCost(Cost);
		item.setSupplier(Supplier);
		item.setQuantity(Quantity);
		item.setQuantityOnOrder(QuantityOnOrder);
		item.setOrderThreshold(Threshold);

		this.inventory.insert(item);
	}

	public void removeItemByNumber(int itemNumber)
	{

		Item item = getItem(itemNumber);
		this.inventory.removeElement(item);
	}

	public Item getItem(int itemNumber)
	{
		Item item = new Item(itemNumber);
		Node<Item> temp = inventory.findNode(item);

		if (temp == null)
		{
			return null;
		}
		else
		{
			return temp.getElement();
		}
	}

	public void updateQuantity(int itemNumber, int count)
	{
		int quantity;
		int quantityThreshod;
		Item item = new Item(itemNumber);

		Node<Item> temp = inventory.findNode(item);

		if (temp == null)
		{
			System.out.println("Error: item not found in inventory");
		}
		else
		{
			quantity = temp.getElement().getQuantity();
			if ((quantity - count) >= 0)
			{
				temp.getElement().setQuantity(quantity - count);
			}
			else
			{
				temp.getElement().setQuantity(0);
			}
			if ((temp.getElement().getQuantity() < temp.getElement().getOrderThreshold())
					&& (temp.getElement().getQuantityOnOrder() == 0))
			{
				temp.getElement().setQuantityOnOrder(temp.getElement().getOrderThreshold() * 2);

			}
		}
	}

	public void updateQuantityOnOrder(int itemNumber, int count)
	{
		int quantity;
		Item item = new Item(itemNumber);

		Node<Item> temp = inventory.findNode(item);

		if (temp == null)
		{
			System.out.println("Error: item not found in inventory");
		}
		else
		{
			quantity = temp.getElement().getQuantityOnOrder();
			if ((quantity - count) >= 0)
			{
				temp.getElement().setQuantityOnOrder(quantity - count);
			}
			else
			{
				temp.getElement().setQuantityOnOrder(0);
			}
		}
	}

	public void updateItem(int itemNumber, String name, double salePrice, double cost, String supplier, int quantity,
			int quantityOnOrder, int threshold)
	{
		Item item = new Item(itemNumber);

		Node<Item> temp = inventory.findNode(item);

		if (temp == null)
		{
			System.out.println("Error: item not found in inventory");
		}
		else
		{
			temp.getElement().setName(name);
			temp.getElement().setSalePrice(salePrice);
			temp.getElement().setCost(cost);
			temp.getElement().setSupplier(supplier);
			temp.getElement().setQuantity(quantity);
			temp.getElement().setQuantityOnOrder(quantityOnOrder);
			temp.getElement().setOrderThreshold(threshold);
		}
	}

	public Node<Item> getRoot()
	{
		return inventory.getRoot();
	}

	public int getNextItemNumber()
	{
		return (int) (Math.random() * 1000) + 1;
	}

	public boolean isEmpyt()
	{
		return inventory.isEmpty();
	}

	public int size()
	{
		return inventory.Size();
	}

	public String reportInventory()
	{
		bigString = "";
		return reportTraversal((inventory.getRoot()));
	}

	public String reportTraversal(Node<Item> node)
	{

		if (node != null)
		{
			reportTraversal(node.getLeft());
			bigString += node.getElement().reportItem();
			// System.out.println(node.getElement().reportItem());

			reportTraversal(node.getRight());
			return bigString;
		}
		else
		{
			return null;
		}

	}

	public Tree<Item> getCurrentInventory()
	{
		Tree<Item> inv = new Tree<Item>();
		inv = inventory;
		return inv;
	}

	public NodeList<Item> buildNodeList(Node<Item> node)
	{
		NodeList<Item> list = new NodeList<Item>();

		if (node != null)
		{
			reportTraversal(node.getLeft());
			list.insertLast(node.getElement());
			reportTraversal(node.getRight());
			return list;
		}
		else
		{
			return null;
		}

	}

	public String reportOrdered()
	{
		orderString = "****************************************************************\n";
		orderString += "                  All ordered Items:\n";
		orderString += "****************************************************************\n";

		return reportOrderedTraverse(inventory.getRoot());
	}

	public String reportOrderedTraverse(Node<Item> node)
	{

		if (node != null)
		{
			reportOrderedTraverse(node.getLeft());
			if (node.getElement().getQuantityOnOrder() > 0)
			{
				orderString += node.getElement().reportItem();
			}
			reportOrderedTraverse(node.getRight());
			return orderString;
		}
		else
		{
			return null;
		}

	}

	public String toString()
	{
		return inventory.toString();
	}

}

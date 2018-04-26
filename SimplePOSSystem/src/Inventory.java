import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Inventory
{
	private NodeList<Item> inventory;
	final String FILE = "Inventory.txt";
	final String FILE_BAK = "Inventory.txt.bak";

	public Inventory()
	{
		this.inventory = new NodeList<Item>();

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
				this.inventory.insertLast(item);
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
		FileWriter fileOut;
		File f = new File(FILE);
		File fbak = new File(FILE + ".bak");

		// If FILE exists, rename it FILE + _date_time.bak
		boolean isFileRenamed = f.renameTo(fbak);

		if (isFileRenamed)
		{
			System.out.println("Inventory bak up created.");
		}
		else
		{
			System.out.println("Error renaming the file");
		}

		try
		{
			fileOut = new FileWriter(FILE);

			Node<Item> node = inventory.getHead().getNext();

			while (node != null)
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

				fileOut.write(stringOut);

			}

			fileOut.close();
		}
		catch (IOException e)
		{
			System.out.println("problem opening output file.");
		}
	}

	public void addItemLast(Item item)
	{
		this.inventory.insertLast(item);
	}

	public void addNewItem(int num, String Name, Double SalePrice, Double Cost, String Supplier, int Quantity,
			int QuantityOnOrder, int Threshold)
	{
		Item item = new Item();
		item.setItemNumber(inventory.last().getItemNumber() + 1);
		item.setName(Name);
		item.setSalePrice(SalePrice);
		item.setCost(Cost);
		item.setSupplier(Supplier);
		item.setQuantity(Quantity);
		item.setQuantityOnOrder(QuantityOnOrder);
		item.setOrderThreshold(Threshold);

		this.inventory.insertLast(item);
	}

	public void removeItemByNumber(int itemNumber)
	{
		Node<Item> node = inventory.find(itemNumber);
		if (node != null)
			this.inventory.remove(node);
	}

	public Item getItem(int itemNumber)
	{
		Node<Item> temp = inventory.find(itemNumber);

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
		Node<Item> temp = inventory.find(itemNumber);

		if (temp == null)
		{
			System.out.println("Error: item not found in inventory");
		}
		else
		{
			quantity = temp.getElement().getQuantity();
			if ((quantity - count) >= 0)
			{
				temp.getElement().setQuantity(quantity + count);
			}
			else
			{
				temp.getElement().setQuantity(0);
			}
		}
	}

	public Item first()
	{
		return inventory.first();
	}

	public Node<Item> getHead()
	{
		return inventory.getHead();
	}

	public Node<Item> getTail()
	{
		return inventory.getTail();
	}

	public Node<Item> getNext(Node<Item> node)
	{
		return inventory.getNext(node);
	}

	public Node<Item> getPrev(Node<Item> node)
	{
		return inventory.getPrev(node);
	}

	public Item last()
	{
		return inventory.last();
	}

	public boolean isEmpyt()
	{
		return inventory.isEmpty();
	}

	public int size()
	{
		return inventory.size();
	}

	public String toString()
	{
		return inventory.toString();
	}

}

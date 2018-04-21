
public class Receipt
{
	private int receiptNumber;
	private NodeList<Item> listOfItems; // Array of <T> may be better?
										// Say 10000 items?
	private double tax;
	private double total;
	private long dateTime;
	private int registerNumber;
	private String cashier;
	private int count;
	private String type;

	public Receipt()
	{

	}

	public Receipt(int num)
	{
		this.receiptNumber = num;
	}

	public Receipt(int receiptNumber, NodeList<Item> listOfItems, double tax, double total, long dateTime,
			int registerNumber, String cashier, String type)
	{
		super();
		this.receiptNumber = receiptNumber;
		this.listOfItems = listOfItems;
		this.tax = tax;
		this.total = total;
		this.dateTime = dateTime;
		this.registerNumber = registerNumber;
		this.cashier = cashier;
		this.type = type;
	}

	public int getReceiptNumber()
	{
		return receiptNumber;
	}

	public void setReceiptNumber(int receiptNumber)
	{
		this.receiptNumber = receiptNumber;
	}

	public Item getListOfItems()
	{
		return listOfItems.first();
	}

	public void setListOfItems(Item item)
	{
		this.listOfItems.insertFirst(item);
	}

	public double getTax()
	{
		return tax;
	}

	public void setTax(double tax)
	{
		this.tax = tax;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public long getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(long dateTime)
	{
		this.dateTime = dateTime;
	}

	public int getRegisterNumber()
	{
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber)
	{
		this.registerNumber = registerNumber;
	}

	public String getCashier()
	{
		return cashier;
	}

	public void setCashier(String cashier)
	{
		this.cashier = cashier;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String toString()
	{
		return ""; // Needs to be figured out
	}

}


public class Item
{
	private int itemNumber;
	private String name;
	private Double salePrice;
	private Double cost;
	private String supplier;
	private int quantity;
	private int orderThreshold;

	public Item()
	{

	}

	public Item(int num)
	{
		itemNumber = num;
	}

	public Item(int num, String Name, Double SalePrice, Double Cost, String Supplier, int Quantity, int Threshold)
	{
		this.itemNumber = num;
		this.name = Name;
		this.salePrice = SalePrice;
		this.cost = Cost;
		this.supplier = Supplier;
		this.quantity = Quantity;
		this.orderThreshold = Threshold;
	}

	public int getItemNumber()
	{
		return itemNumber;
	}

	public void setItemNumber(int itemNumber)
	{
		this.itemNumber = itemNumber;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getSalePrice()
	{
		return salePrice;
	}

	public void setSalePrice(Double salePrice)
	{
		this.salePrice = salePrice;
	}

	public Double getCost()
	{
		return cost;
	}

	public void setCost(Double cost)
	{
		this.cost = cost;
	}

	public String getSupplier()
	{
		return supplier;
	}

	public void setSupplier(String supplier)
	{
		this.supplier = supplier;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getOrderThreshold()
	{
		return orderThreshold;
	}

	public void setOrderThreshold(int quantity)
	{
		this.orderThreshold = quantity;
	}

	public String toString()
	{
		return itemNumber + " " + name + " " + quantity;
	}

}

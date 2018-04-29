import java.text.NumberFormat;

public class TransactionItem
{
	private Item item;
	private int qty;
	private double itemTotal;
	private NumberFormat fmt = NumberFormat.getCurrencyInstance();

	public TransactionItem(Item item, int qty)
	{
		this.qty = qty;
		this.item = item;
		itemTotal = qty * item.getSalePrice();
	}

	public double getItemTotal()
	{
		return itemTotal;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public String toString()
	{

		return String.format("%-15s%-13s%-15s%-1s", item.getItemNumber(), item.getName(),
				(qty + " @ " + fmt.format(item.getSalePrice())), fmt.format(itemTotal));

	}

}

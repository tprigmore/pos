import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Receipt
{
	private String receiptNumber;
	private String userName;
	private Date transactionTime;
	private String dateStr;
	private final DateFormat DATEFORM = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa");
	private ArrayList<TransactionItem> List;
	private Double amtTotal;
	private Double taxTotal;
	private Double subTotal;

	private NumberFormat fmt = NumberFormat.getCurrencyInstance();

	public Receipt(String userName, ArrayList<TransactionItem> List, Double amtTotal, Double taxTotal, Double subTotal)
	{
		this.userName = userName;
		this.List = List;
		this.amtTotal = amtTotal;
		this.taxTotal = taxTotal;
		this.subTotal = subTotal;

		transactionTime = new Date();
		dateStr = DATEFORM.format(transactionTime);

		receiptNumber = userName + "_" + dateStr;

	}

	public String toString()
	{
		String result = "********************************************************\n";

		result += "Receipt Number: " + receiptNumber + "\n\nCashier: " + userName + "\n" + "Date: " + dateStr + "\n\n";

		result += String.format("%-15s%-13s%-15s%-1s", "Item Number", "Item Name", "Qty & Price", "Item Subtotal")
				+ "\n";

		for (int i = 0; i < List.size(); i++)
		{
			result += List.get(i).toString() + "\n";
		}

		result += "\n" + "Subtotal: " + fmt.format(subTotal) + "\nTax Total: " + fmt.format(taxTotal) + "\nTotal: "
				+ fmt.format(amtTotal);

		result += "\n";

		return result;
	}

}

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Drawer
{
	private int registerNumber;
	private Date startTime;
	private Date endTime;
	private DateFormat dateForm;
	private final double STARTMONEY = 300;
	private double moneyOnHand;
	private double endMoney;
	private double processSale;
	private double processReturn;
	String username;
	private ArrayList<Receipt> listOfReceipts;

	public Drawer(String username)
	{
		this.username = username;
		listOfReceipts = new ArrayList<Receipt>();
		this.dateForm = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa");
		startTime = new Date();
		moneyOnHand = STARTMONEY;

	}

	public void processTransaction(double saleAmt)
	{
		moneyOnHand = moneyOnHand + saleAmt;
	}

	public double getMoney()
	{
		return moneyOnHand;
	}

	public void setMoney(double money)
	{
		this.moneyOnHand = money;
	}

	public int getRegisterNumber()
	{
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber)
	{
		this.registerNumber = registerNumber;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public String getStartTimeString()
	{
		return dateForm.format(startTime);
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public String getEndTimeString()
	{
		return dateForm.format(endTime);
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public double getSTARTMONEY()
	{
		return STARTMONEY;
	}

	public double getEndMoney()
	{
		return endMoney;
	}

	public void setEndMoney(double endMoney)
	{
		this.endMoney = endMoney;
	}

	public static void main(String[] args)
	{
		DateFormat dateForm = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa");
		Date date = new Date();
		System.out.println(dateForm.format(date));

	}

}

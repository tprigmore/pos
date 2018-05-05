import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Drawer
{
	private int registerNumber;
	private Date startTime;
	private DateFormat dateForm;
	private final double STARTMONEY = 300;
	private double moneyOnHand;
	private double processSale;
	private double processReturn;
	String username;
	private ArrayList<Receipt> listOfReceipts;

	private NumberFormat fmt = NumberFormat.getCurrencyInstance();

	public Drawer(String username)
	{
		this.username = username;
		listOfReceipts = new ArrayList<Receipt>();
		this.dateForm = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa");
		startTime = new Date();
		moneyOnHand = STARTMONEY;
		registerNumber = 1;

	}

	public String getUsername()
	{
		return username;
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

	public double getSTARTMONEY()
	{
		return STARTMONEY;
	}

	public void addReciept(Receipt receipt)
	{
		listOfReceipts.add(receipt);
	}

	public void endShift()
	{
		String userDirLocation = System.getProperty("user.dir");
		File userDir = new File(userDirLocation);

		String result = "Shift Start Time: " + getStartTimeString() + " End Time: " + dateForm.format(new Date()) + "\n"
				+ "User: " + getUsername() + "\n" + "Register Number: " + getRegisterNumber() + "\n" + "Total: "
				+ fmt.format(getMoney() - 300.00) + "\n";

		for (int i = 0; i < listOfReceipts.size(); i++)
		{
			result += listOfReceipts.get(i).toString();
		}

		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd'_'HH_mm");
		Date dNow = new Date();
		String tempDate = ft.format(dNow);

		String fileName = "reg" + getRegisterNumber() + "_" + getUsername() + "_" + tempDate + ".txt";

		try
		{
			File f = new File(userDir + "//drawer//" + fileName);
			FileWriter fileOut = new FileWriter(f);
			fileOut.write(result);
			fileOut.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String reportDrawer()
	{
		String userDirLocation = System.getProperty("user.dir");
		File userDir = new File(userDirLocation);
		// default to user directory

		double total = 0.0;
		double actualTotal = 0.0;
		double discrepancy = 0.0;
		FileWriter fileOut = null;
		String line = "";

		JFileChooser chooser = new JFileChooser(userDir + "//drawer");

		int status = chooser.showOpenDialog(null);
		if (status != JFileChooser.APPROVE_OPTION)
			return ("No File Chosen");
		else
		{
			File fileIn = chooser.getSelectedFile();
			try
			{
				fileOut = new FileWriter(fileIn + "V");

				String info = "";

				Scanner scan = new Scanner(fileIn);
				// line 1
				if (scan.hasNext())
				{
					line = scan.nextLine();
					info += line + "\n";
					fileOut.write(line + "\n");
				}
				// line 2
				if (scan.hasNext())
				{
					line = scan.nextLine();
					info += line + "\n";
					fileOut.write(line + "\n");
				}
				// line 3
				if (scan.hasNext())
				{
					line = scan.nextLine();
					info += line + "\n";
					fileOut.write(line + "\n");
				}
				// line 4
				if (scan.hasNext())
				{

					line = scan.nextLine();
					info += line + "\n";
					fileOut.write(line + "\n");
					total = Double.parseDouble(line.substring(8, line.length()));
				}
				// line 5
				if (scan.hasNext())
				{

					line = scan.nextLine();
					if (!line.substring(0, 6).equalsIgnoreCase("Actual"))
					{
						boolean flag = true;
						while (flag)
						{
							try
							{
								actualTotal = Double
										.parseDouble(JOptionPane.showInputDialog(this, "Type in actual drawer total:"));
								flag = false;
							}
							catch (Exception e)
							{
								flag = true;
							}
						}

						discrepancy = total - actualTotal;
						info += "Actual Total: " + fmt.format(actualTotal) + "\n";
						fileOut.write("Actual Total: " + fmt.format(actualTotal) + "\n");
						info += "Descrepancy: " + fmt.format(discrepancy) + "\n";
						fileOut.write("Descrepancy: " + fmt.format(discrepancy) + "\n");
					}
					info += line + "\n";
					fileOut.write(line + "\n");
				}

				while (scan.hasNext())
				{
					line = scan.nextLine();
					info += line + "\n";
					fileOut.write(line + "\n");
				}
				fileOut.close();
				return (info);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "";
	}

	public static void main(String[] args)
	{
		DateFormat dateForm = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa");
		Date date = new Date();
		System.out.println(dateForm.format(date));

	}

}

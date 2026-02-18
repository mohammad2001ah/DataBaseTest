package MyTestCase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataInfo {
	Random rand = new Random();

	Connection con;
	
	String myname = "ahmad";

	Statement stmt;

	ResultSet rs;

	WebDriver driver = new ChromeDriver();

	String firstname;
	String customerID;
	String customerPhone;

	String lastname;

	int randomEmailNumber = rand.nextInt(5478);
	int randomEmailNumber2 = rand.nextInt(978);

	String MyWebSite = "https://automationteststore.com/";
	

}

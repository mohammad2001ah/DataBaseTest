package MyTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestData {
	Connection con;
	Statement stmt;
	ResultSet rs;

	String firstname;
	String customerID;
	String customerPhone;

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MyBeforeTest() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "2001");

	}

	@Test(priority = 1, enabled = false)
	public void MtTestToAddData() throws SQLException {
		stmt = con.createStatement();
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) "
				+ "VALUES (2011, 'Mohammad', 'Abu Alhijaa', 'Mohammad', '+962-7-9550-1284', 'Amman Street', NULL, 'Amman', NULL, '11008', 'Jordan', 1076, 75007.00);";
		stmt.executeUpdate(query);
	}

	@Test(priority=2)
	public void ReadData() throws SQLException {
		stmt = con.createStatement();
		String Query="select * from customers where customerNumber=2011";
		rs=stmt.executeQuery(Query);
		while(rs.next()) {
			 firstname=rs.getString("customerName");
			 customerID=rs.getString("customerNumber");
			 customerPhone=rs.getString("phone");
			
			System.out.println(firstname);
			System.out.println(customerID);	
			System.out.println(customerPhone);	
		}

	}
}

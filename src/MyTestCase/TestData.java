package MyTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestData {
	Random rand = new Random();
	
	Connection con;
	Statement stmt;
	ResultSet rs;

	String firstname;
	String lastname;
	String customerID;
	String customerPhone;
	
	int randomEmailNumber = rand.nextInt(5478);
	int randomEmailNumber2 = rand.nextInt(978);

	String MyWebSite = "https://automationteststore.com/";

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MyBeforeTest() throws SQLException {
		driver.get(MyWebSite);

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "2001");

	}

	@Test(priority = 1, enabled = false)
	public void MtTestToAddData() throws SQLException {
		stmt = con.createStatement();
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) "
				+ "VALUES (2011, 'Mohammad', 'Abu Alhijaa', 'Mohammad', '+962-7-9550-1284', 'Amman Street', NULL, 'Amman', NULL, '11008', 'Jordan', 1076, 75007.00);";
		stmt.executeUpdate(query);
	}

	@Test(priority = 2)
	public void ReadData() throws SQLException {
		stmt = con.createStatement();
		String Query = "select * from customers where customerNumber=2011";
		rs = stmt.executeQuery(Query);
		while (rs.next()) {
			firstname = rs.getString("customerName");
			lastname = rs.getString("contactLastName");
			customerID = rs.getString("customerNumber");
			customerPhone = rs.getString("phone");

			System.out.println(firstname);
			System.out.println(customerID);
			System.out.println(customerPhone);
		}
	}

	@Test(priority = 3,enabled=true)
	public void SignupWithDataBase() throws InterruptedException {
		String TheEmail = firstname + lastname + randomEmailNumber + randomEmailNumber2 + "@gmail.com";
		System.out.println(TheEmail);
		WebElement LoginAndSignUpButton = driver.findElement(By.linkText("Login or register"));
		LoginAndSignUpButton.click();
		// to press on countinue button
		WebElement CountinueButtonBeforeSignupPage = driver.findElement(By.xpath("//button[@title='Continue']"));
		CountinueButtonBeforeSignupPage.click();

		// ---------------- you are inside the signup page -----------------

		// elements

		WebElement FirstNameInputField = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement LastNameInputField = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement CountryDropDown = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement StateDropDown = driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		WebElement PostalInput = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement ConditionsAndTermsCheckbox = driver.findElement(By.id("AccountFrm_agree"));
		WebElement CountinueButton = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));

// Actions 

		FirstNameInputField.sendKeys(firstname);
		LastNameInputField.sendKeys(lastname);
		EmailInput.sendKeys(TheEmail);
		AddressInput.sendKeys("Amman");
		Select CountrySelect = new Select(CountryDropDown);
		CountrySelect.selectByValue("108");
		Thread.sleep(3000);
		int randomState = rand.nextInt(StateDropDown.findElements(By.tagName("option")).size());
		Select SelectforStateDropDown = new Select(StateDropDown);
		SelectforStateDropDown.selectByIndex(randomState);
		CityInput.sendKeys("RandomCity");
		PostalInput.sendKeys(customerPhone);
		ConditionsAndTermsCheckbox.click();
	}
	@Test(priority = 4,enabled = true)
	public void myTestToupdateData() throws SQLException {

		stmt = con.createStatement();

		String query = "UPDATE customers SET customerName = 'dana and raghad' WHERE customerNumber = 9991";

		stmt.executeUpdate(query);
	}
	@Test(priority = 5,enabled = true)
	public void myTestToDeleteData() throws SQLException {
		
		stmt = con.createStatement();
		
		String query = "delete from customers WHERE customerNumber = 9991";
		
		stmt.executeUpdate(query);
	}
	
}

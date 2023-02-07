package assignment11;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestNg_Assignment10 {

	List<WebElement> list1;
	List<WebElement> list3;
	List<WebElement> listc;
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browser) {
		driver = Utility.browser(browser);
		driver.get("https://ineuron-courses.vercel.app/login");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1)
	public void Login() throws InterruptedException {

		// Login to application
		// driver.get("https://ineuron-courses.vercel.app/login");

		Assert.assertEquals("iNeuron Courses", driver.getTitle());
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("ineuron@ineuron.ai");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("ineuron");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		WebElement manage = driver.findElement(By.xpath("//span[normalize-space()='Manage']"));

		Assert.assertTrue(manage.isDisplayed());

	}

	@Test(priority = 2)
	public void CreateCourse() throws InterruptedException {

		driver.findElement(By.xpath("//span[normalize-space()='Manage']")).click();

		Actions a = new Actions(driver);

		a.moveToElement(driver.findElement(By.xpath("//div[@class='manage-course']"))).perform();
		driver.findElement(By.xpath("//div[@class='manage-btns']/button[3]")).click();
		driver.findElement(By.id("thumbnail")).sendKeys("C:\\Users\\kriti\\OneDrive\\Desktop\\New folder\\test.JPG");
		driver.findElement(By.id("name")).sendKeys("Playwright");
		driver.findElement(By.id("description")).sendKeys("UI automation tool");
		driver.findElement(By.id("instructorNameId")).sendKeys("Kritika");
		driver.findElement(By.id("price")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@name='startDate']")).click();

		WebElement date = driver.findElement(By.xpath("//div[contains(@class,'current-month')]"));
		while (!date.getText().equals("August 2023")) {
			driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();

		}

		List<WebElement> list = driver
				.findElements(By.xpath("//div[@class='react-datepicker__week']//div[@role='option']"));

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().equals("10")) {
				list.get(i).click();
				break;
			}
		}

		driver.findElement(By.xpath("//input[@name='endDate']")).click();

		WebElement enddate = driver.findElement(By.xpath("//div[contains(@class,'current-month')]"));
		while (!enddate.getText().equals("November 2023")) {
			driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();

		}
		List<WebElement> list2 = driver
				.findElements(By.xpath("//div[@class='react-datepicker__week']//div[@role='option']"));

		for (int i = 0; i < list.size(); i++) {

			// System.out.println(list2.get(i).getText());

			if (list2.get(i).getText().equals("10")) {
				list2.get(i).click();
				break;
			}
		}

		driver.findElement(By.xpath("//button[@class='menu-btn']")).click();

		List<WebElement> l = driver.findElements(By.xpath("//div[@class='menu-items']/button"));

		for (int i = 0; i < l.size(); i++) {

			if (l.get(i).getText().equals("Testing"))

			{
				l.get(i).click();
				break;
			}

		}

		driver.findElement(By.xpath("//button[@class='action-btn']")).click();

		// validate number of courses after adding course

		Thread.sleep(3000);
		list1 = driver.findElements(By.xpath("//tr/td/input[@type='checkbox']"));

		Thread.sleep(3000);

		Thread.sleep(2000);

		System.out.println(list1.size()); // validation number of courses

	}

	// Delete the Course
	@Test(priority = 3)
	public void Delete() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,800)");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//td[contains(text(),'Playwright')]//parent::tr/td/input")).click();

		driver.findElement(By.xpath("//td[contains(text(),'Playwright')]//parent::tr/td/button")).click();
		Thread.sleep(4000);

		// validation number of courses
		list3 = driver.findElements(By.xpath("//tr/td/input[@type='checkbox']"));
		System.out.println(list3.size()); // validation number of courses

		Assert.assertNotSame(list1.size(), list3.size());

	}

	@Test(priority = 4)
	public void tearDown() {
		driver.quit();
	}

}

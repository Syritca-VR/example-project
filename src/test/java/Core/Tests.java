package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

public class Tests {

    public static WebDriver driver;
    static Properties xpath = new Properties();
    static Properties selector = new Properties();

    @BeforeMethod
    public static void setUp() throws IOException, InterruptedException {
        xpath.load(new FileInputStream("./locators/xpath.properties"));
        selector.load(new FileInputStream("./locators/selector.properties"));
       // Common.startServer();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void tearDown() throws IOException {
        driver.quit();
      //  Common.terminateServer();
    }

    @Test(priority = 1, testName = "test1")  // negative test
    public void test1() throws InterruptedException {
        driver.get("http://localhost:8080/phonebook-api.html");
        Thread.sleep(5000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_userid_contacts"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_userid_contacts_try_it_out_button"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(selector.getProperty("get_users_userid_contacts_description"))).sendKeys("0");
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_userid_contacts_execute_button"))).click();
        Thread.sleep(1000);
        String userLink = driver.findElement(By.xpath(xpath.getProperty("get_users_userid_contacts_request_url"))).getText();
        Thread.sleep(1000);
        driver.get(userLink);
        Thread.sleep(3000);
        String userDetails = driver.findElement(By.xpath(xpath.getProperty("json_request"))).getText();
        String result = Common.jsonReader(userDetails, "status");
        Thread.sleep(1000);
        if (!result.equals("200")) {
            throw new AssertionError("error " + result);
        }
    }

    @Test(priority = 2, testName = "test2")  // positive test
    public void test2() throws InterruptedException {
        driver.get("http://localhost:8080/phonebook-api.html");
        Thread.sleep(5000);
        driver.findElement(By.xpath(xpath.getProperty("get_user_userid_contact_contactid"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_user_userid_contact_contactid_try_it_out_button"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(selector.getProperty("get_user_userid_contact_contactid_userid"))).sendKeys("1");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(selector.getProperty("get_user_userid_contact_contactid_contactid"))).sendKeys("1");
        driver.findElement(By.xpath(xpath.getProperty("get_user_userid_contact_contactid_execute_button"))).click();
        Thread.sleep(1000);
        String link = driver.findElement(By.xpath(xpath.getProperty("get_user_userid_contact_contactid_request_url"))).getText();
        driver.get(link);
        Thread.sleep(1000);
        String userDetails = driver.findElement(By.xpath(xpath.getProperty("json_request"))).getText();
        String result = Common.jsonReader(userDetails, "id");
        if (!result.equals("1")) {
            throw new AssertionError("there is no user with id: 1");
        }
    }

    @Test(priority = 3, testName = "test3")  // positive test
    public void test3() throws InterruptedException {
        driver.get("http://localhost:8080/phonebook-api.html");
        Thread.sleep(5000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_try_it_out_button"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(selector.getProperty("get_users_usersid_usersid"))).sendKeys("2");
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_execute_button"))).click();
        Thread.sleep(1000);
        String link = driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_request_url"))).getText();
        Thread.sleep(1000);
        driver.get(link);
        Thread.sleep(3000);
        String userDetails = driver.findElement(By.xpath(xpath.getProperty("json_request"))).getText();
        Thread.sleep(1000);
        String result = Common.jsonReader(userDetails, "id");
        if (!result.equals("2")) {
            throw new AssertionError("there is no user with id: 2");
        }
    }

    @Test(priority = 4, testName = "test4")  // negative test
    public void test4() throws InterruptedException {
        driver.get("http://localhost:8080/phonebook-api.html");
        Thread.sleep(5000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_try_it_out_button"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(selector.getProperty("get_users_usersid_usersid"))).sendKeys("2");
        Thread.sleep(1000);
        driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_execute_button"))).click();
        Thread.sleep(1000);
        String link = driver.findElement(By.xpath(xpath.getProperty("get_users_usersid_request_url"))).getText();
        Thread.sleep(1000);
        driver.get(link);
        Thread.sleep(3000);
        String userDetails = driver.findElement(By.xpath(xpath.getProperty("json_request"))).getText();
        Thread.sleep(1000);
        String result = Common.jsonReader(userDetails, "lastName");
        if (!result.equals("Wick")) {
            throw new AssertionError("there is no user with last name Wick");
        }
    }
}
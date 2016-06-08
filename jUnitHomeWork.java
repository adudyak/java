/**
 * Created by adudyak on 6/8/2016.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class jUnitHomeWork {

    // Create object of interface WebDriver.
    WebDriver driver;

    //Annotation that sends to JUnit message that this method should be executed BEFORE test method
    @Before
    public void setUp() {
		/*
		 * Initialize object driver. According to internal realization of selenium
		 * we can initialize driver object as
		 * WebDriver driver = new FirefoxDriver();
		 * or
		 * WebDriver driver = new ChromeDriver();
		 * not
		 * WebDriver driver = new WebDriver();
		 * but be careful with other stuff :-)
		 */
        driver = new FirefoxDriver();

        //Maximize window of FF
        driver.manage().window().maximize();
    }

    //Annotation that sends to JUnit message that this method should be executed as TEST method
    @Test
    public void testJUnitWebDriver() {
        //Open FF and go to https://google.com.ua. Also you can use this variant -> driver.navigate().to("https://google.com.ua");
        driver.get("https://google.com.ua");

        //Send request string to the search form
        driver.findElement(By.name("q")).sendKeys("Geeksforless");

        //Dynamic wait for results
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='st']")));

        //Add results to list
        ArrayList<String> resultsList = new ArrayList<String>();

        for (int i = 1; i < 6; i++) {
            resultsList.add(driver.findElement(By.xpath(".//*[@id='rso']/div/div["+i+"]/div/h3/a")).getAttribute("href"));
            resultsList.add(driver.findElement(By.xpath("//*[@id='rso']/div/div["+i+"]/div/div/div/span")).getText());
        }

        //Print results
        resultsList.forEach(System.out::println);

        //Open link
        driver.get(driver.findElement(By.xpath("//a[@href='http://geeksforless.com/']")).getAttribute("href"));


        //Wait for site to appear
        WebElement myDynamicElement2 = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='http://geeksforless.com']")));
    }

    //Annotation that sends to JUnit message that this method should be executed AFTER test method
    @After
    public void tearDown() {
        //Close browser and finish work of driver
        driver.quit();
    }
}
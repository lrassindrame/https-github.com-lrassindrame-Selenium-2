import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;

import static org.junit.Assert.*;

import java.net.MalformedURLException;


public class SeleniumTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    //Fill simple form and send (eg. Login)
    public void loginSuccess(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("Selenium2", "Selenium2");
        assertEquals(userPage.getUserName(), "Selenium2");
    }

    @Test
    //Fill simple form and send (eg. Login)
    public void testLoginFail(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("wrong", "wrong");
        assertEquals(userPage, null);
    }

    @Test
    //Fill input (select)
    public void testGetTopPlayingGamePS42024(){
        MainPage mainPage = new MainPage(driver);
        StatsPage statsPage = mainPage.toStatsPage();
        assertTrue(statsPage.topPlayingPS42024().contains("Persona 3 Reload"));
    }

    @Test 
    //logout
    public void testLogout(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("Selenium2", "Selenium2");
        mainPage = userPage.logout();
        //Checking the return to the main page after logout
        assertTrue(mainPage.getTitle().contains("HowLongToBeat: The Game"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

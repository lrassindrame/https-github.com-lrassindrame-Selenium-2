import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AllTopicForumPage;
import pages.CreateReplyPage;
import pages.FirstTopicForumPage;
import pages.ForumPage;
import pages.LoginPage;
import pages.MainPage;
import pages.StatsPage;
import pages.UserOptionPage;
import pages.UserPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.Map;


public class SeleniumTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    //Static Page test and Reading the page title
    public void testStaticPage(){
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.getTitle().contains("HowLongToBeat.com | Game Lengths, Backlogs and more!"));
    }

    @Test
    //Multiple page test with map and reading the pages title
    public void testMultiplePage(){
        Map<String, String> urlTitleMap = new HashMap<>();

        urlTitleMap.put("https://howlongtobeat.com/", "HowLongToBeat.com | Game Lengths, Backlogs and more!");
        urlTitleMap.put("https://howlongtobeat.com/game/68033", "How long is Baldur's Gate 3? | HowLongToBeat");
        urlTitleMap.put("https://howlongtobeat.com/feedback", "Site Feedback | HowLongToBeat");

        for (Map.Entry<String, String> urlTitle : urlTitleMap.entrySet()) {
            driver.get(urlTitle.getKey());
            assertTrue(driver.getTitle().contains(urlTitle.getValue()));
        }
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
    //Fill input (select) / Filling and reading drop-down
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
        assertTrue(mainPage.getTitle().contains("HowLongToBeat.com | Game Lengths, Backlogs and more!"));
    }

    @Test 
    //Form sending with user (reply to a forum)
    public void testReplyForumWithUser(){
        //Access to the page to reply to a forum
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("Selenium2", "Selenium2");
        ForumPage forumPage = userPage.toForumPage();
        AllTopicForumPage allTopicForumPage = forumPage.toAlltopicsPage();
        FirstTopicForumPage firstTopicForumPage = allTopicForumPage.toFirstTopicForumPage();
        CreateReplyPage createReplyPage = firstTopicForumPage.toCreateReplyPage();
        //Sending the reply and verifying its presence in the latest reply
        FirstTopicForumPage firstTopicForumPage2 = createReplyPage.sendReply("send a test reply");
        assertTrue(firstTopicForumPage2.getLastReply().contains("send a test reply"));
    }

    @Test
    //Send a form (user option change, filling radio button)
    public void testChangeUserOption(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("Selenium2", "Selenium2");
        UserOptionPage userOptionPage = userPage.toOptionPage();
        //Change a user option and verify that it is applied correctly
        UserOptionPage userOptionPage2 = userOptionPage.selectMale();
        UserPage userPage2 = userOptionPage2.toUserPage();
        assertTrue(userPage2.getGenderInfo().contains("Male"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

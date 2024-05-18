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
        options.addArguments("--delete-cookies");
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
    // test WebDriver configuration (with small window)
    public void testSmallWindowSize() throws MalformedURLException{
        //Closing the default driver to open one with a small window
        close();
        setupSmallWindowSize();
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.getTitle().contains("HowLongToBeat.com | Game Lengths, Backlogs and more!"));
        //Reopening the default driver
        close();
        setup();
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
        UserPage userPage = loginAndOpenUserPage();
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
        UserPage userPage = loginAndOpenUserPage();
        MainPage mainPage = userPage.logout();
        //Checking the return to the main page after logout
        assertTrue(mainPage.getTitle().contains("HowLongToBeat.com | Game Lengths, Backlogs and more!"));
    }

    @Test 
    //Form sending with user (reply to a forum)
    public void testReplyForumWithUser(){
        //Access to the page to reply to a forum
        UserPage userPage = loginAndOpenUserPage();
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
        UserOptionPage userOptionPage = openUserOptionPage();
        //Change a user option and verify that it is applied correctly
        UserOptionPage userOptionPage2 = userOptionPage.selectMale();
        UserPage userPage2 = userOptionPage2.toUserPage();
        assertTrue(userPage2.getGenderInfo().contains("Male"));
    }

    @Test
    // Hover test
    public void testHover(){
        MainPage mainPage = new MainPage(driver);
        assertNotEquals(mainPage.buttonBackground(), mainPage.buttonHoverBackground());
    }

    @Test
    // test File Upload
    public void testUpload(){
        UserOptionPage userOptionPage = openUserOptionPage();
        assertNotEquals(userOptionPage.uploadAvatar(), null);
    }

    @Test
    public void testBrowserBack(){
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.browserBack().contains("HowLongToBeat.com | Game Lengths, Backlogs and more!"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    
    public void setupSmallWindowSize()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=800,600");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    public UserPage loginAndOpenUserPage(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.toLoginPage();
        UserPage userPage = loginPage.login("Selenium2", "Selenium2");
        return userPage;
    }

    public UserOptionPage openUserOptionPage(){
        UserPage userPage = loginAndOpenUserPage();
        return userPage.toOptionPage();
    }
}

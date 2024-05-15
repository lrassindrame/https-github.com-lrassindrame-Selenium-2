import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {
    private By loginPageLocator = By.xpath("//*[@id=\"__next\"]/div/header/nav/ul[2]/li[1]/a");


    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://howlongtobeat.com/");
    }    

    public LoginPage toLoginPage(){
        WebElement loginPage = waitAndReturnElement(loginPageLocator);
        loginPage.click();
        return new LoginPage(this.driver);
    }
    
}

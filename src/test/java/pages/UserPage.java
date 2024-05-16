package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class UserPage extends PageBase{
    private final By usernameLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div/div[1]/div[3]");
    private final By logoutButtonLocator = By.xpath("//*[@id=\"__next\"]/div/header/div/div/div[2]/ul/li[9]/a");
    private final By ForumButtonLocator = By.xpath("//*[@id=\"__next\"]/div/header/nav/ul[1]/li[1]/a");
    private final By OptionButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div/div[2]/div/ul/li[6]/a");
    private final By GenderLocator = By.xpath("//*[@id=\"user_info\"]/div[1]/div[1]/text()");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        WebElement usernameElement = waitAndReturnElement(usernameLocator);
        return usernameElement.getText();
    }

    public MainPage logout(){
        WebElement logoutElement = waitAndReturnElement(logoutButtonLocator);
        logoutElement.click();
        return new MainPage(driver);
    }

    public ForumPage toForumPage(){
        WebElement forumButtonElement = waitAndReturnElement(ForumButtonLocator);
        forumButtonElement.click();
        return new ForumPage(driver);
    }
 
    public UserOptionPage toOptionPage(){
        WebElement optionElement = waitAndReturnElement(OptionButtonLocator);
        optionElement.click();
        return new UserOptionPage(driver);
    }

    public String getGenderInfo(){
        WebElement genderElement = waitAndReturnElement(GenderLocator);
        return genderElement.getText();
    }
}

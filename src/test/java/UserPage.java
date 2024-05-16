import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class UserPage extends PageBase{
    private final By usernameLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div/div[1]/div[3]");
    private final By logoutButtonLocator = By.xpath("//*[@id=\"__next\"]/div/header/div/div/div[2]/ul/li[9]/a");
    private final By ForumButtonLocator = By.xpath("//*[@id=\"__next\"]/div/header/nav/ul[1]/li[1]/a");

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
 
}

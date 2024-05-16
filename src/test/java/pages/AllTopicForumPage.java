package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class AllTopicForumPage extends PageBase{

    private final By firstTopicButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div/div[1]/div/ul/li[1]/div/div[3]/a");

    public AllTopicForumPage(WebDriver driver) {
        super(driver);
    }

    public FirstTopicForumPage toFirstTopicForumPage(){
        WebElement firstTopicButton = waitAndReturnElement(firstTopicButtonLocator);
        firstTopicButton.click();
        return new FirstTopicForumPage(driver);
    }
    
}

package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class ForumPage extends PageBase{
    private final By AllButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div[3]/div[1]/div/ul/li[2]/a");


    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public AllTopicForumPage toAlltopicsPage(){
        WebElement allTopicForumButton = waitAndReturnElement(AllButtonLocator);
        allTopicForumButton.click();
        return new AllTopicForumPage(driver);
    }
    
}

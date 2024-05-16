package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class FirstTopicForumPage extends PageBase{

    private final By replyButtonLocator = By.xpath("//*[@id=\"forum_top\"]/a[1]");
    private final By lastReplyLocator = By.xpath("(//*[@class='content_100'])[last()]");

    public FirstTopicForumPage(WebDriver driver) {
        super(driver);
    }

    //Accessing the form for sending a reply to a forum with user
    public CreateReplyPage toCreateReplyPage(){
        WebElement replyButtonElement = waitAndReturnElement(replyButtonLocator);
        replyButtonElement.click();
        return new CreateReplyPage(driver);
    }

    public String getLastReply(){
        WebElement lastReplyElement = waitAndReturnElement(lastReplyLocator);
        return lastReplyElement.getText();
    }
    
}

package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class CreateReplyPage extends PageBase{
    private final By inputReplyLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div/div[1]/div/form/fieldset[1]/textarea");
    private final By postButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div/div[1]/div/form/fieldset[3]/input");

    public CreateReplyPage(WebDriver driver) {
        super(driver);
    }
    
    //Form sending with user (reply to a forum)
    public FirstTopicForumPage sendReply(String reply){
        WebElement inputReplyElement = waitAndReturnElement(inputReplyLocator);
        inputReplyElement.sendKeys(reply);

        WebElement postButtonElement = waitAndReturnElement(postButtonLocator);
        postButtonElement.click();

        return new FirstTopicForumPage(driver);
    }
}

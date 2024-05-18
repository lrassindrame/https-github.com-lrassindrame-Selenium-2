package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class UserOptionPage extends PageBase{
    private final By profilButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[1]/div/div/div[2]/div/ul/li[1]/a");
    private final By checkBoxMaleLocator = By.id("gender_male");
    private final By saveButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[2]/div/div[2]/div[1]/div/input");
    private final By inputAvatarLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[2]/div/div[2]/div[2]/fieldset[2]/input");
    private final By saveAvatarLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[2]/div/div[2]/div[2]/div/input");
    private final By errorMessageLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[2]/div/div[2]/div[2]/fieldset[2]/p");

    public UserOptionPage(WebDriver driver) {
        super(driver);
    }
    
    public UserPage toUserPage(){
        WebElement profilButtonElement = waitAndReturnElement(profilButtonLocator);
        profilButtonElement.click();
        return new UserPage(driver);
    }

    //Send a form (user option change, filling radio button)
    public UserOptionPage selectMale(){
        WebElement checkBoxElement = waitAndReturnElement(checkBoxMaleLocator);
        checkBoxElement.click();

        WebElement saveElement = waitAndReturnElement(saveButtonLocator);
        saveElement.click();

        return new UserOptionPage(driver);
    }

    //File Upload
    public UserOptionPage uploadAvatar(){
        WebElement inputAvatarElement = waitAndReturnElement(inputAvatarLocator);
        inputAvatarElement.sendKeys("../../../../images/avatar.png");

        WebElement saveAvatarElement = waitAndReturnElement(saveAvatarLocator);
        saveAvatarElement.click();

        WebElement messageElement = waitAndReturnElement(errorMessageLocator);
        if(messageElement.getText().contains("Must be less than 500kb in size.")){
            return null;
        }
        else{
            return new UserOptionPage(driver);
        }
    }
}

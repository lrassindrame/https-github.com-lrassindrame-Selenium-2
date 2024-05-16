import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserOptionPage extends PageBase{
    private final By profilButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[1]/div/div/div[2]/div/ul/li[1]/a");
    private final By checkBoxMaleLocator = By.id("gender_male");
    private final By saveButtonLocator = By.xpath("//*[@id=\"__next\"]/div/main/form/div[2]/div/div[2]/div[1]/div/input");

    public UserOptionPage(WebDriver driver) {
        super(driver);
    }
    
    public UserPage toUserPage(){
        WebElement profilButtonElement = waitAndReturnElement(profilButtonLocator);
        profilButtonElement.click();
        return new UserPage(driver);
    }

    //Send a form (user option change)
    public UserOptionPage selectMale(){
        WebElement checkBoxElement = waitAndReturnElement(checkBoxMaleLocator);
        checkBoxElement.click();

        WebElement saveElement = waitAndReturnElement(saveButtonLocator);
        saveElement.click();

        return new UserOptionPage(driver);
    }
}

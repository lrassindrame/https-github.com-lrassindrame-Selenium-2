import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends PageBase{
    private final By usernameLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div/div[1]/div[3]");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        WebElement usernameElement = waitAndReturnElement(usernameLocator);
        return usernameElement.getText();
    }
    
}

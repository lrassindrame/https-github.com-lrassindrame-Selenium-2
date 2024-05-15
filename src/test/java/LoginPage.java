import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase{

    private final By usernameLocator = By.id("user_name");
    private final By passwordLocator = By.name("password");
    private final By loginButtonLocator = By.name("Submit");
    private final By loginErrorMessageLocator = By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div/div[1]/div/div");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Fill simple form and send (eg. Login)
    public UserPage login(String username, String password){
        WebElement usernameElement = waitAndReturnElement(usernameLocator);
        usernameElement.sendKeys(username);

        WebElement passwordElement = waitAndReturnElement(passwordLocator);
        passwordElement.sendKeys(password);

        WebElement loginButtonElement = waitAndReturnElement(loginButtonLocator);
        loginButtonElement.click();

        WebElement loginErrorMessageElement = waitAndReturnElement(loginErrorMessageLocator);
        if(loginErrorMessageElement.getText().contains("Invalid Credentials!")){
            return null;
        }
        else{
            return new UserPage(this.driver);
        }
    }

    
}

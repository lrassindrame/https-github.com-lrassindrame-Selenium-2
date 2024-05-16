import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class StatsPage extends PageBase{
    private final By selectPlatformLocator = By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[1]/div[1]/select");
    private final By selectYearLocator = By.xpath("//*[@id=\"showyear\"]");
    private final By topPlayingResultLocator = By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[2]/div[8]/div/table/tbody/tr[1]/td[2]/a");


    public StatsPage(WebDriver driver) {
        super(driver);
    }
    
    //Fill input (select) / Filling and reading drop-down
    public String topPlayingPS42024(){
        WebElement selectPlatformElement = waitAndReturnElement(selectPlatformLocator);
        Select select = new Select(selectPlatformElement);
        select.selectByVisibleText("PlayStation 4");

        WebElement selectYearElement = waitAndReturnElement(selectYearLocator);
        Select select2 = new Select(selectYearElement);
        select2.selectByVisibleText("2024");

        WebElement topPlayingResultElement = waitAndReturnElement(topPlayingResultLocator);
        return topPlayingResultElement.getText();
    }
}

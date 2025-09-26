package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageHelper {

    private final WebDriver driver;

    public MainPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstItemPrice() {
        return driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_price")).getText();
    }

    public void openFirstItemPage() {
        driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_name")).click();
    }
}

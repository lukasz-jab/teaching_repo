package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPageHelper {

    private final WebDriver driver;

    public ProductPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getPrice() {
        return driver.findElement(By.cssSelector("div.inventory_details div.inventory_details_price")).getText();
    }

    public void addToCart() {
        driver.findElement(By.cssSelector("button[data-test='add-to-cart']")).click();
    }

}

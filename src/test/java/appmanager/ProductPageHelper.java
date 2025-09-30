package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void backToProducts() {
        driver.findElement(By.xpath("//div[@data-test='secondary-header']//button[@data-test='back-to-products']")).click();
    }
}

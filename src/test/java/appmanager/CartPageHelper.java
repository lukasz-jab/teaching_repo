package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPageHelper {

    private final WebDriver driver;

    public CartPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void cartIconBtn() {
        driver.findElement(By.cssSelector("div#shopping_cart_container a[data-test='shopping-cart-link']")).click();
    }

    public String getNumberFromIconCart() {
        return driver.findElement(By.cssSelector("div#shopping_cart_container span[data-test=shopping-cart-badge]")).getText();
    }

    public String getItemPriceFromCartSite() {
        return driver.findElement(By.cssSelector("div.inventory_item_price[data-test='inventory-item-price']")).getText();
    }
}

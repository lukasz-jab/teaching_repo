package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPageHelper {

    private final WebDriver driver;

    public CartPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void cartIconBtn() {
        driver.findElement(By.cssSelector("div#shopping_cart_container a[data-test='shopping-cart-link']")).click();
    }

    public String getNumberFromIconCart() {
        if (isElementPresent(By.cssSelector("div#shopping_cart_container span[data-test=shopping-cart-badge]"))) {
            return driver.findElement(By.cssSelector("div#shopping_cart_container span[data-test=shopping-cart-badge]")).getText();
        } else {
            return "0";
        }
    }

    public String getItemPriceFromCartSite() {
        return driver.findElement(By.cssSelector("div.inventory_item_price[data-test='inventory-item-price']")).getText();
    }

    public void continueShopping() {
        driver.findElement(By.xpath("//div[contains(@class, 'cart_footer')]//button[@data-test='continue-shopping']")).click();
    }

    public boolean isElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
    }
}

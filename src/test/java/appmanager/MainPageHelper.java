package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public List<Product> getProducts() {
        List<Product> allProducts = new ArrayList<>();
        List<WebElement> products = driver.findElements(By.cssSelector("div.inventory_list div.inventory_item"));
        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector("a div[data-test='inventory-item-name']")).getText();
            String description = product.findElement(By.cssSelector("div[data-test='inventory-item-desc']")).getText();
            String price = product.findElement(By.cssSelector("div[data-test='inventory-item-price']")).getText();

            allProducts.add(new Product().withName(name).withPrice(price).withDescription(description));
        }
        return allProducts;
    }

    public void openProductPage(Product randomMainPageProduct) {
        //driver.findElement(By.xpath("//div[@data-test='inventory-item']//a//div[contains(text(), '" + randomMainPageProduct.getTitle() + "')]")).click();
        driver.findElement(By.xpath(String.format("//div[@data-test='inventory-item']//a//div[contains(text(), '%s')]", randomMainPageProduct.getTitle()))).click();
    }
}

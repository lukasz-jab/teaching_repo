import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class SauceShops {

    WebDriver driver;
    String firstItemPrice = "";

    @BeforeClass
    public void setup() {
        //avoid Chrome Unnecessary Alerts
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("credentials_enable_service", false);

        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testShop() {
        //login:
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();

        // get first item price:
        firstItemPrice = driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_price")).getText();
        System.out.println("firstItemPrice: " + firstItemPrice);

        // open first item page:
        driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_name")).click();

        // get item price on item page (details page):
        String firstItemPriceOnItemPage = driver.findElement(By.cssSelector("div.inventory_details div.inventory_details_price")).getText();
        System.out.println("firstItemPriceOnItemPage: " + firstItemPriceOnItemPage);

        // assertion that price on main page are equal price on item page:
        Assert.assertEquals(firstItemPrice, firstItemPriceOnItemPage);

        // added product to cart from product page
        driver.findElement(By.cssSelector("button[data-test='add-to-cart']")).click();

        //get number from cart icon:
        String itemNumber = driver.findElement(By.cssSelector("div#shopping_cart_container span[data-test=shopping-cart-badge]")).getText();
        // assertion that 1 appear on cart icon
        Assert.assertEquals(itemNumber, "1");

        //goto cart from cart icon:
        driver.findElement(By.cssSelector("div#shopping_cart_container a[data-test='shopping-cart-link']")).click();

        //get item price from cart site
        String itemPriceOnCartSite = driver.findElement(By.cssSelector("div.inventory_item_price[data-test='inventory-item-price']")).getText();

        // assertion that price on cart page are equal price on item page:
        Assert.assertEquals(itemPriceOnCartSite, firstItemPrice);

        // goto to checkout page
        driver.findElement(By.cssSelector("button#checkout")).click();

        // Switched to XPATH selectors:
        // fill checkout inputs
        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys("Name");
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("LastName");
        driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys("LastName");

       // continue from checkout site
        driver.findElement(By.xpath("//input[@type='submit'][@id='continue']")).click();

        // get Item total value:
        String itemTotal = driver.findElement(By.xpath("//div[@data-test='subtotal-label']")).getText();
        System.out.println("itemTotal: " + itemTotal);
        // get Tax value:
        String tax = driver.findElement(By.xpath("//div[@data-test='tax-label']")).getText();
        System.out.println("tax: " + tax);
        // get Total value:
        String total = driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();
        System.out.println("total: " + total);

        Assert.assertEquals(itemTotal, "Item total: $29.99");
        Assert.assertEquals(tax, "Tax: $2.40");
        Assert.assertEquals(total, "Total: $32.39");

        // assertion that price on main page are equal price on checkout overview page:
        String itemPriceOnCheckoutOverview = driver.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText();
        Assert.assertEquals(firstItemPrice, itemPriceOnCheckoutOverview);

        // goto finish:
        driver.findElement(By.xpath("//button[@name='finish' and @data-test='finish']")).click();

        //Assertion information on checkout completed site:
        String informationText = driver.findElement(By.xpath("//h2[contains(@class, 'complete-header')]")).getText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }

    @AfterClass
    public void stop() {
        driver.quit();
    }
}

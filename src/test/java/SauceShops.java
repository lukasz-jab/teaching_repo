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
        login();
        firstItemPrice = getFirstItemPrice();
        openFirstItemPage();

        String firstItemPriceOnItemPage = getItemPriceOnItemPage();
        Assert.assertEquals(firstItemPrice, firstItemPriceOnItemPage);

        addedProductToCartFromProductPage();

        String itemNumber = getNumberFromIconCart();
        Assert.assertEquals(itemNumber, "1");

        cartIconBtn();

        String itemPriceOnCartSite = getItemPriceFromCartSite();
        Assert.assertEquals(itemPriceOnCartSite, firstItemPrice);

        gotoCheckoutPage();
        fillCheckoutData();
        continueFromCheckoutSite();
        String itemTotal = getItemTotalValue();
        String tax = getTaxValue();
        String total = getTotalValue();

        Assert.assertEquals(itemTotal, "Item total: $29.99");
        Assert.assertEquals(tax, "Tax: $2.40");
        Assert.assertEquals(total, "Total: $32.39");

        String itemPriceOnCheckoutOverview = getItemPriceOnCheckoutSummaryPage();
        Assert.assertEquals(firstItemPrice, itemPriceOnCheckoutOverview);

        finishOrder();

        String informationText = getSuccessConfirmationText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }

    public void login() {
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();
    }

    public String getFirstItemPrice() {
        return driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_price")).getText();
    }

    public String getItemPriceOnItemPage() {
         return driver.findElement(By.cssSelector("div.inventory_details div.inventory_details_price")).getText();
    }

    public void addedProductToCartFromProductPage() {
        driver.findElement(By.cssSelector("button[data-test='add-to-cart']")).click();
    }

    public String getItemPriceFromCartSite() {
        return driver.findElement(By.cssSelector("div.inventory_item_price[data-test='inventory-item-price']")).getText();
    }

    public String getNumberFromIconCart() {
        return driver.findElement(By.cssSelector("div#shopping_cart_container span[data-test=shopping-cart-badge]")).getText();
    }

    public void cartIconBtn() {
        driver.findElement(By.cssSelector("div#shopping_cart_container a[data-test='shopping-cart-link']")).click();
    }

    private void fillCheckoutData() {
        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys("Name");
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("LastName");
        driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys("PostCode");
    }

    public void openFirstItemPage() {
        driver.findElement(By.cssSelector("div.inventory_item div.inventory_item_name")).click();
    }

    public void gotoCheckoutPage() {
        driver.findElement(By.cssSelector("button#checkout")).click();
    }

    public void continueFromCheckoutSite() {
        driver.findElement(By.xpath("//input[@type='submit'][@id='continue']")).click();
    }

    public String getItemTotalValue() {
        return driver.findElement(By.xpath("//div[@data-test='subtotal-label']")).getText();
    }

    public String getTaxValue() {
        return driver.findElement(By.xpath("//div[@data-test='tax-label']")).getText();
    }

    public String getTotalValue() {
        return driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();
    }

    public String getItemPriceOnCheckoutSummaryPage() {
        return driver.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText();
    }

    public void finishOrder() {
        driver.findElement(By.xpath("//button[@name='finish' and @data-test='finish']")).click();
    }

    public String getSuccessConfirmationText() {
        return driver.findElement(By.xpath("//h2[contains(@class, 'complete-header')]")).getText();
    }

    @AfterClass
    public void stop() {
        driver.quit();
    }
}

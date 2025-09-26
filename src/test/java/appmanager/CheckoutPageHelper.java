package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPageHelper {

    private final WebDriver driver;

    public CheckoutPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCheckoutData() {
        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys("Name");
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("LastName");
        driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys("PostCode");
    }

    public void gotoPage() {
        driver.findElement(By.cssSelector("button#checkout")).click();
    }

    public void continueFromCheckoutSite() {
        driver.findElement(By.xpath("//input[@type='submit'][@id='continue']")).click();
    }

    public String getNettoValue() {
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
}

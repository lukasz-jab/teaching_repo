package tests;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class SauceShopsTest extends TestBase {

    String randomItemPrice = "";
    Product randomMainPageProduct;

    @BeforeClass
    public void login() {
        app.session().login();
    }

    @Test(priority = 1)
    public void testOpenMainPage() {
        randomMainPageProduct = new HashSet<Product>(app.mainPage().getProducts()).iterator().next();
        randomItemPrice = randomMainPageProduct.getPrice();
    }

    @Test(priority = 2)
    public void testProductPageInfo() {
        app.mainPage().openProductPage(randomMainPageProduct);

        String firstItemPriceOnItemPage = app.productPage().getPrice();
        Assert.assertEquals(randomItemPrice, firstItemPriceOnItemPage);
    }

    @Test(priority = 3)
    public void testAddToCart() {
        app.productPage().addToCart();

        String itemNumber = app.cartPage().getNumberFromIconCart();
        Assert.assertEquals(itemNumber, "1");
    }

    @Test(priority = 4)
    public void testCartPageInfo() {
        app.cartPage().cartIconBtn();

        String itemPriceOnCartSite = app.cartPage().getItemPriceFromCartSite();
        Assert.assertEquals(itemPriceOnCartSite, randomItemPrice);
    }

    @Test(priority = 5)
    public void testFillCheckoutInputs() {
        app.checkoutPage().gotoPage();
        app.checkoutPage().fillCheckoutData();
    }

    @Test(priority = 6)
    public void testCheckoutInfo() {
        app.checkoutPage().continueFromCheckoutSite();
        String itemTotal = app.checkoutPage().getNettoValue();
        String tax = app.checkoutPage().getTaxValue();
        String total = app.checkoutPage().getTotalValue();

        BigDecimal taxInfo = new BigDecimal(randomMainPageProduct.getPrice().replaceAll("\\$", "")).multiply(new BigDecimal("0.08")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalInfo = new BigDecimal(randomMainPageProduct.getPrice().replaceAll("\\$", "")).add(taxInfo);

        Assert.assertEquals(itemTotal, "Item total: " + randomMainPageProduct.getPrice());
        Assert.assertEquals(tax, "Tax: $" + taxInfo);
        Assert.assertEquals(total, "Total: $" + totalInfo);

        String itemPriceOnCheckoutOverview = app.checkoutPage().getItemPriceOnCheckoutSummaryPage();
        Assert.assertEquals(randomItemPrice, itemPriceOnCheckoutOverview);
    }

    @Test(priority = 7)
    public void testFinishedOrderInfo() {
        app.checkoutPage().finishOrder();

        String informationText = app.checkoutPage().getSuccessConfirmationText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }
}

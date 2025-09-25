package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceShopsTest extends TestBase {

    String firstItemPrice = "";

    @Test(priority = 1)
    public void testOpenMainPage() {
        app.login();
        firstItemPrice = app.getFirstItemPrice();
    }

    @Test(priority = 2)
    public void testProductPageInfo() {
        app.openFirstItemPage();

        String firstItemPriceOnItemPage = app.getItemPriceOnItemPage();
        Assert.assertEquals(firstItemPrice, firstItemPriceOnItemPage);
    }

    @Test(priority = 3)
    public void testAddToCart() {
        app.addedProductToCartFromProductPage();

        String itemNumber = app.getNumberFromIconCart();
        Assert.assertEquals(itemNumber, "1");
    }

    @Test(priority = 4)
    public void testCartPageInfo() {
        app.cartIconBtn();

        String itemPriceOnCartSite = app.getItemPriceFromCartSite();
        Assert.assertEquals(itemPriceOnCartSite, firstItemPrice);
    }

    @Test(priority = 5)
    public void testFillCheckoutInputs() {
        app.gotoCheckoutPage();
        app.fillCheckoutData();
    }

    @Test(priority = 6)
    public void testCheckoutInfo() {
        app.continueFromCheckoutSite();
        String itemTotal = app.getItemTotalValue();
        String tax = app.getTaxValue();
        String total = app.getTotalValue();

        Assert.assertEquals(itemTotal, "Item total: $29.99");
        Assert.assertEquals(tax, "Tax: $2.40");
        Assert.assertEquals(total, "Total: $32.39");

        String itemPriceOnCheckoutOverview = app.getItemPriceOnCheckoutSummaryPage();
        Assert.assertEquals(firstItemPrice, itemPriceOnCheckoutOverview);
    }

    @Test(priority = 7)
    public void testFinishedOrderInfo() {
        app.finishOrder();

        String informationText = app.getSuccessConfirmationText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }
}

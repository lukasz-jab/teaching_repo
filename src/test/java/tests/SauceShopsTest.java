package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SauceShopsTest extends TestBase {

    String firstItemPrice = "";

    @BeforeClass
    public void login() {
        app.session().login();
    }

    @Test(priority = 1)
    public void testOpenMainPage() {
        //app.session().login();
        firstItemPrice = app.mainPage().getFirstItemPrice();
    }

    @Test(priority = 2)
    public void testProductPageInfo() {
        app.mainPage().openFirstItemPage();

        String firstItemPriceOnItemPage = app.productPage().getPrice();
        Assert.assertEquals(firstItemPrice, firstItemPriceOnItemPage);
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
        Assert.assertEquals(itemPriceOnCartSite, firstItemPrice);
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

        Assert.assertEquals(itemTotal, "Item total: $29.99");
        Assert.assertEquals(tax, "Tax: $2.40");
        Assert.assertEquals(total, "Total: $32.39");

        String itemPriceOnCheckoutOverview = app.checkoutPage().getItemPriceOnCheckoutSummaryPage();
        Assert.assertEquals(firstItemPrice, itemPriceOnCheckoutOverview);
    }

    @Test(priority = 7)
    public void testFinishedOrderInfo() {
        app.checkoutPage().finishOrder();

        String informationText = app.checkoutPage().getSuccessConfirmationText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }
}

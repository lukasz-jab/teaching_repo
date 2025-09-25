package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceShops extends TestBase {

    String firstItemPrice = "";


    @Test
    public void testShop() {
        app.login();
        firstItemPrice = app.getFirstItemPrice();
        app.openFirstItemPage();

        String firstItemPriceOnItemPage = app.getItemPriceOnItemPage();
        Assert.assertEquals(firstItemPrice, firstItemPriceOnItemPage);

        app.addedProductToCartFromProductPage();

        String itemNumber = app.getNumberFromIconCart();
        Assert.assertEquals(itemNumber, "1");

        app.cartIconBtn();

        String itemPriceOnCartSite = app.getItemPriceFromCartSite();
        Assert.assertEquals(itemPriceOnCartSite, firstItemPrice);

        app.gotoCheckoutPage();
        app.fillCheckoutData();
        app.continueFromCheckoutSite();
        String itemTotal = app.getItemTotalValue();
        String tax = app.getTaxValue();
        String total = app.getTotalValue();

        Assert.assertEquals(itemTotal, "Item total: $29.99");
        Assert.assertEquals(tax, "Tax: $2.40");
        Assert.assertEquals(total, "Total: $32.39");

        String itemPriceOnCheckoutOverview = app.getItemPriceOnCheckoutSummaryPage();
        Assert.assertEquals(firstItemPrice, itemPriceOnCheckoutOverview);

        app.finishOrder();

        String informationText = app.getSuccessConfirmationText();
        Assert.assertEquals(informationText, "Thank you for your order!");
    }
}

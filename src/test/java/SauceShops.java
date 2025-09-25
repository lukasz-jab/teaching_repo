import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceShops extends TestBase {

    String firstItemPrice = "";


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
}

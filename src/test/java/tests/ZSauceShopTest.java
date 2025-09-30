package tests;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;

public class ZSauceShopTest extends TestBase {

    Product product;

    @BeforeClass
    public void login() {
        app.session().login();
    }

    @Test(priority = 1)
    public void testBackToProducts() {
        product = new HashSet<Product>(app.mainPage().getProducts()).iterator().next();
        app.mainPage().openProductPage(product);
        app.productPage().backToProducts();

        Assert.assertTrue(app.mainPage().getProducts().size() > 0);
        Assert.assertTrue(app.session().getSiteAddr().contains("inventory.html"));
    }

    @Test(priority = 2)
    public void testMainPageCartOperations() {
        app.mainPage().addToCart(product);

        Assert.assertEquals("1", app.cartPage().getNumberFromIconCart());

        app.mainPage().openCart();
        app.cartPage().continueShopping();

        Assert.assertTrue(app.mainPage().getProducts().size() > 0);
        Assert.assertTrue(app.session().getSiteAddr().contains("inventory.html"));
    }

    @Test(priority = 3)
    public void testRemoveProductFromCartFromMainPage() {
        app.mainPage().removeFromCart(product);

        Assert.assertEquals("0", app.cartPage().getNumberFromIconCart());
    }
}

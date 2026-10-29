package com.saucedemo.playwright;

import com.microsoft.playwright.*;
import com.saucedemo.playwright.page.CartPage;
import com.saucedemo.playwright.page.LoginPage;
import com.saucedemo.playwright.page.ProductsPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class souceLogin {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    Page page;

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;


    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void tearDown() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    private void performLogin(String username) {
        loginPage = new LoginPage(page);
        loginPage.navigate();
        productsPage = loginPage.login(username, "secret_sauce");
    }

    @Test
    void loginSuccessfully(){
        performLogin("standard_user");
        Assertions.assertTrue(page.url().contains("/inventory.html"), "El login no fue exitoso.");
    }

    @Test
    void purchaseComplete(){
        performLogin("standard_user");
        productsPage.addBAckPackToCart();
        cartPage = productsPage.goToCart();
        cartPage.startCheckout();
        cartPage.fillCheckoutInfo("Juan", "Colmenares", "7000006");
        cartPage.finishPurchase();
        Assertions.assertTrue(
                cartPage.getSuccessMessage().contains("Thank you"),
                "El mensaje de compra exitosa no se encontró."
        );
        productsPage.logout();
    }

    @Test
    void deleteProduct(){
        performLogin("standard_user");
        productsPage.addBAckPackToCart();
        Locator removeButton = productsPage.getBackpackRemoveButton();
        Assertions.assertFalse(removeButton.isHidden(), "El botón 'REMOVE' debería ser visible después de agregar.");
        cartPage = productsPage.goToCart();
        cartPage.removeBackpack();
        Assertions.assertTrue(page.locator("#remove-sauce-labs-backpack").isHidden(), "El producto no se eliminó del carrito.");
        productsPage = cartPage.continueShopping();
        productsPage.logout();
    }

    @Test
    void sortByPriceHigherLower(){
        performLogin("standard_user");
        productsPage.sortBy("hilo");
        List<Double> prices = productsPage.getAllProductPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());
        Assertions.assertEquals(sortedPrices, prices, "Los productos no están ordenados de mayor a menor precio.");
    }

    @Test
    void sortByPriceLowerHigher(){
        performLogin("standard_user");
        productsPage.sortBy("lohi");
        List<Double> prices = productsPage.getAllProductPrices();
        Assertions.assertTrue(
                prices.get(0) <= prices.get(1),
                "Los primeros dos elementos no están ordenados de menor a mayor precio."
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"error_user"})
    void doesntChangeButton(String username){
        performLogin(username);
        productsPage.addBAckPackToCart();
        Locator removeButton = productsPage.getBackpackRemoveButton();
        removeButton.click();
        Assertions.assertTrue(removeButton.isHidden(), "El botón 'REMOVE' sigue visible después de hacer click.");
    }
}
package com.parabank;

import com.microsoft.playwright.*;
import com.parabank.page.HomePage;
import com.parabank.page.RegisterPage;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class TestParabank {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;

    Page page;

    @BeforeAll
    static void setUpBrowser() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );
    }

    @BeforeEach
    void setUp() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }


    @Test
    void registerSuccessfully(){
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
        fillPersonalData();
        page.locator("#customer\\.username").fill("user8");
        page.locator("#customer\\.password").fill("password1");
        page.locator("#repeatedPassword").fill("password1");
        page.locator("input.button[value='Register']").click();
        Assertions.assertTrue(page.locator("h1.title").textContent().contains("user8") &&
                page.locator("#rightPanel").textContent().contains("Your account was created successfully. You are now logged in."));
    }
    @Test
    void createNewAccount(){
        String username = "user" + String.valueOf(System.currentTimeMillis());;
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
        fillPersonalData();
        createCredentials(username);
        registerUser(username);
        createAccount();
    }

    @Test
    void testTransferFounds() {
        String username = "user" + String.valueOf(System.currentTimeMillis());;
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
        fillPersonalData();
        createCredentials(username);
        registerUser(username);
        transferFounds();
    }

    @Test
    void xpathTesting(){
        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
        page.locator("//a[.='Register']").click();

        page.locator("//input[@id='customer.firstName']").fill("juan");

        page.locator("//input[@id='customer.lastName']").fill("colmenares");

        page.locator("//input[@value='Register']").click();

        Assertions.assertTrue(page.locator("//span[@id='customer.username.errors']").textContent().contains("is required"));

    }



    private void fillPersonalData(){
        RegisterPage registerPage = new RegisterPage();
        page.locator(registerPage.firstNameTxt).fill("juan");

        page.locator(registerPage.lastNameTxt).fill("colmenares");

        page.locator(registerPage.addressStreetTxt).fill("street 85");

        page.locator(registerPage.addressCityTxt).fill("new york");

        page.locator(registerPage.addressStateTxt).fill("arizona");

        page.locator(registerPage.addressZipCodeTxt).fill("40001");

        page.locator(registerPage.phoneNumberTxt).fill("1123456789");

        page.locator(registerPage.ssnTxt).fill("1041680930");



    }



    private void createCredentials(String usuario){
        RegisterPage registerPage = new RegisterPage();
        page.locator(registerPage.usernameTxt).fill(usuario);

        page.locator(registerPage.passwordTxt).fill("password1");

        page.locator(registerPage.passwordTxt).fill("password1");

        page.locator(registerPage.repeatedPasswordTxt).fill("password1");

        page.locator(registerPage.registerBtn).click();



    }



    private void registerUser(String username){

        page.locator("input.button[value='Register']").click();

        Assertions.assertTrue(page.locator("h1.title").textContent().contains(username) &&

                page.locator("#rightPanel").textContent().contains("Your account was created successfully. You are now logged in."));

    }



    private void transferFounds(){

        page.navigate("https://parabank.parasoft.com/parabank/transfer.htm");

        page.locator("#amount").fill("40");

        page.locator("input.button[value='Transfer']").click();

        String expectedTitle = "Transfer Complete!";

        Assertions.assertTrue(

                page.locator("#showResult").textContent().contains(expectedTitle)

        );

    }



    private void createAccount(){

        page.locator("//a[.='Open New Account']").click();

        page.locator("//input[@class='button']").click();

        Assertions.assertTrue(page.locator("//h1[.='Account Opened!']").textContent().contains("Account Opened!"));

    }

}

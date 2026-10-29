
package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import jdk.javadoc.doclet.Doclet;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ASimplePlaywrightTest {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
     Page page;

    @BeforeAll
    public static void setupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox","--disable-extensions","--disable-gpu"))
        );
        browserContext = browser.newContext();

    }

    @BeforeEach
    public void setUp() {
        page = browserContext.newPage();

    }
    @AfterAll
    public static void teardown(){
        browser.close();
        playwright.close();
    }


    @Test
    void shouldshowThePageTitle(){

        page.navigate("https://practicesoftwaretesting.com");

        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
    @Test
    void shouldSearchByKeyWord(){
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
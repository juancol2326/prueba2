package selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyCandyTest {

    @Test
    public void testMyCandy() {
        // Asegúrate de que la ruta sea correcta
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com/");
            // Aquí puedes agregar más interacciones con la página
            WebElement usernameTxt = driver.findElement(By.id("user-name"));
            WebElement passwordTxt = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.id("login-button"));
            usernameTxt.sendKeys("standard_user");
            passwordTxt.sendKeys("secret_sauce");
            loginBtn.click();
            WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
            Assertions.assertTrue(cartIcon.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el stack trace para ver el error
        } finally {
            if (driver != null) {
                driver.quit(); // Asegúrate de cerrar el navegador si fue inicializado
            }
    }}}

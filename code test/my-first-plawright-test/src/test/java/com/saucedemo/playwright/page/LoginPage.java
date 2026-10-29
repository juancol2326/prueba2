package com.saucedemo.playwright.page;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String url = "https://www.saucedemo.com/";
    private final String usernameInput = "[data-test=username]";
    private final String passwordInput = "[data-test=password]";
    private final String loginButton = "#login-button";

    public LoginPage(Page page){
        this.page = page;
    }
    public void navigate(){
        page.navigate(url);

    }
    public ProductsPage login(String username, String password){
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
        return new ProductsPage(page);
    }
}

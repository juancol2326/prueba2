package com.saucedemo.playwright.page;

import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;


    private final String checkoutButton = "[data-test=checkout]";
    private final String firstNameInput = "[data-test=firstName]";
    private final String lastNameInput = "[data-test=lastName]";
    private final String zipCodeInput = "[data-test=postalCode]";
    private final String continueButton = "[data-test=continue]";
    private final String finishButton = "[data-test=finish]";
    private final String removeBackPackButton = "[data-test=remove-sauce-labs-backpack]";
    private final String continueShoppingButton = "[data-test=continue-shopping]";

    private final String successHeader = ".complete-header";



    public CartPage(Page page){
        this.page = page;
    }

    public void startCheckout() {
        page.click(checkoutButton);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String zipCode){
        page.fill(firstNameInput, firstName);
        page.fill(lastNameInput, lastName);
        page.fill(zipCodeInput, zipCode);
        page.click(continueButton);
    }

    public void finishPurchase(){
        page.click(finishButton);

    }

    public String getSuccessMessage(){
        return page.locator(successHeader).textContent();
    }

    public void removeBackpack(){
        page.click(removeBackPackButton);
    }

    public ProductsPage continueShopping(){
        page.click(continueShoppingButton);
        return new ProductsPage(page);
    }


}
